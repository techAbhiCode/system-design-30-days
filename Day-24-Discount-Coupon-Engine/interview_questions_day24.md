# 6 Essential Interview Questions: Day 24 (Bridge Pattern + Discount Coupon Engine)

### 1. Why did we use the Bridge Pattern instead of standard inheritance or just the Strategy Pattern?
**Answer:** 
* **Why not standard inheritance?** Standard inheritance causes **Class Explosion**. If we have $M$ coupon business categories (Seasonal, Loyalty, Bulk, Bank) and $N$ discount calculation methods (Flat, Percentage, Capped Percentage), direct inheritance requires $M \times N$ classes. If we introduce 3 new categories and 2 new calculations, we must write dozens of new subclasses.
* **Why Bridge?** The Bridge pattern decouples the **Abstraction** (`Coupon` business rule) from the **Implementation** (`DiscountStrategy` mathematical calculation) via composition. The coupon hierarchy and the strategy hierarchy can vary completely independently, reducing the total class count from $M \times N$ to $M + N$.
* **Strategy vs. Bridge:** The Strategy pattern typically focuses on letting a client switch a single algorithm at runtime. The Bridge pattern is an architectural structural pattern that connects two separate, multi-class hierarchies (the Abstraction hierarchy and the Implementor hierarchy) through a common interface bridge.

---

### 2. How does the Chain of Responsibility handle coupon combinability and exclusive offers?
**Answer:** 
In `Coupon.java`, each coupon holds a `next` pointer forming a linked execution chain. 
1. When `applyDiscount(Cart cart)` is triggered, the coupon checks if it is applicable. If applicable, it computes and deducts the discount from the running cart total.
2. It then inspects `isCombinable()`. 
   * If `isCombinable() == true` (default), the execution cascades to `next.applyDiscount(cart)`.
   * If `isCombinable() == false` (e.g., an exclusive clearance code or heavy welcome bonus), the method immediately returns without invoking `next.applyDiscount(cart)`, terminating the discount chain.

---

### 3. What is the difference between applying discounts on the original cart total vs. the running (reduced) cart total? How do e-commerce systems handle this?
**Answer:** 
* **Original Total:** Discounts are always calculated against the raw subtotal of items. For example, if Cart Total = ₹1,000, Coupon A (10%) gives ₹100, and Coupon B (10%) gives ₹100, totaling ₹200 off (Final = ₹800).
* **Running Total (Compounded/Cascaded):** Each coupon calculates its discount on the *remaining* balance. With Cart Total = ₹1,000, Coupon A (10%) gives ₹100 (Total becomes ₹900). Coupon B (10%) gives 10% of ₹900 = ₹90 (Final = ₹810).
* **Real-world Industry Standard:** Category-specific and item-level coupons (like seasonal clothing offers) apply to item subtotals first. Then, order-level promotions (bulk, loyalty) apply to the updated subtotal. Finally, payment/banking coupons apply to the net payable amount before final payment authorization.

---

### 4. How do you prevent race conditions when a limited-edition coupon (e.g., "FIRST1000") is claimed concurrently during a flash sale?
**Answer:** 
In high-throughput flash sales, database row locking (`SELECT FOR UPDATE`) causes database bottlenecks and timeouts.
1. **Atomic In-Memory Counter via Redis:** Store the coupon quota in Redis:
   ```bash
   SET coupon:FIRST1000:quota 1000
   ```
2. **Atomic Decrement (Lua Script / DECRBY):** When a user attempts to apply the coupon, execute an atomic Redis Lua script:
   * Check if `quota > 0`.
   * If yes, decrement quota and add `userId` to a Redis Set `coupon:FIRST1000:claimed` to prevent duplicate claims by the same user.
   * If quota reaches 0, immediately reject subsequent requests without hitting the primary database.
3. **Optimistic Locking at Checkout:** In the final payment transaction in PostgreSQL/MySQL, verify the coupon status using a version check before committing the order.

---

### 5. How do you handle partial order cancellations and refunds when a stacked coupon was applied?
**Answer:** 
Discounts must be **proportionally distributed across eligible items** at the time of checkout rather than deducted as an arbitrary lump sum from the cart:
* If a customer buys Item A (₹800) and Item B (₹200) with a ₹100 flat discount, the discount is apportioned:
  * Item A discount: $\frac{800}{1000} \times 100 = ₹80$ (Net price = ₹720)
  * Item B discount: $\frac{200}{1000} \times 100 = ₹20$ (Net price = ₹180)
* If the user returns Item B, they receive a refund of ₹180, not the full ₹200.
* Furthermore, if the return drops the total below a required threshold (e.g., a bulk discount requiring $\ge$ ₹1000), the system recalculates eligibility and claws back the invalidated promotional difference from the refund amount.

---

### 6. In a production enterprise system, why might you replace concrete Coupon classes with a Rule Engine?
**Answer:** 
While concrete classes like `SeasonalOffer` and `LoyaltyDiscount` are excellent for demonstrating object-oriented principles in an LLD interview, they have severe limitations in production:
1. **Deployment Overhead:** Creating a new coupon would require writing Java code, running tests, compiling binaries, and deploying backend microservices.
2. **Business Team Agility:** Marketing and operations teams need to launch, modify, schedule, and disable promotions dynamically multiple times a day without engineering involvement.
3. **Solution:** Enterprise platforms use **Dynamic Rule Engines** (e.g., Drools, Easy Rules, or custom JSON/AST expressions). Coupon criteria (predicates, limits, caps, stacking flags) are stored in a database or distributed cache and evaluated at runtime against the cart state.
