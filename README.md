# 📦 Customizable E-Commerce System

## 🧾 Description

This project is a customizable e-commerce system designed as a **mobile application** for selling books online. It aims to provide a **flexible**, **modular**, and **scalable** architecture that businesses can easily adapt to their needs. 

The project leverages well-known design patterns to enhance maintainability and extensibility, ensuring a seamless and dynamic user experience.

---

## 🎯 Project Goals

- Create a modular system to manage books, customers, payments, and invoices.
- Enable real-time updates for customers on their order statuses.
- Provide flexible payment options using interchangeable strategies.
- Ensure consistent and efficient state transitions for orders.
- Separate object creation logic (e.g., invoice types) using factory methods.

---

## 🏗️ Applied Design Patterns

### ✅ Observer + State + Singleton

Used together in the **Order Management** module:

- **Observer**: Notifies customers of order status changes in real-time.
- **State**: Manages order stages (`Pending`, `Shipped`, `Delivered`) with encapsulated behavior.
- **Singleton**: Ensures only one instance of each state exists, maintaining consistency.

### ✅ Strategy Pattern

Used in **Payment Processing**:

- Defines an `IPaymentStrategy` interface.
- Implements strategies like `CreditCardPayment` and `PayPalPayment`.
- Allows the system to switch between payment methods without modifying existing code.

### ✅ Factory Method Pattern

Used in **Invoice Generation**:

- Defines an `IInvoiceFactory` interface and a `ConcreteInvoiceFactory` class.
- Generates either `DigitalInvoice` or `PhysicalInvoice` depending on the context.
- Promotes extensibility for future invoice types.

---

## 📱 Platform

- Developed as a **mobile-first** application.
- Ensures users can access and manage purchases anytime, anywhere.

---

## 🧩 UML Diagram

![UML Diagram](./uml-diagram.png)

> Make sure to place the UML diagram image (`uml-diagram.png`) in the root directory of the project or adjust the path accordingly.

--- 


## ✅ Conclusion

This project has deepened our understanding of how design patterns can be effectively implemented to build adaptable and maintainable software systems. By integrating multiple patterns, we were able to design a robust architecture capable of handling real-world business requirements with ease.

