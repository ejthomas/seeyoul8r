# Implementation design

Example input: 1 + 2 * 3 - 4 / 5

# Key class: `Expression`

Representing mathematical expression understandable by the calculator, which can be evaluated to give a literal value.

Has an `evaluate()` abstract method, returning a `Literal`, which is overridden in each subclass to apply the desired operation. 

# `Literal` (extends `Expression`)

Represents a numerical value (may be integer or floating point). Evaulation of a `Literal` returns itself.

# `Operation` (abstract, extends `Expression`)

Represents action of an operator on one or more operands. Operation may be unary e.g. `Factorial`, `Cosine`; may be binary e.g. `Addition`, `Division`. Abstract subclasses `UnaryOperation` and `BinaryOperation` disambiguate these cases. Different mathematical operations are expressed as subclasses of these.

Overrides `evaluate()` to apply the corresponding operator to its (evaluated) operand expression(s).

Base `Operation` class has a `public final int` member `numOperands` allowing user to access the number of operands the described operator acts on. This will probably be removed. The immediate subclasses set this value correspondingly with their constructors, which take this ... consider making the `numOperands` a class variable rather than setting in constructor so that it can be checked before instantiation.

# Operator precedence

Unary operations are applied first, then the BIDMAS operator precedence rule is applied. This means that brackets are recursed upon before performing other operations, and expressions without parentheses are evaluated in the order *indices* (I), *division* (D), *multiplication* (M), *addition* (A) and *subtraction* (S).

To implement operator precedence on user-provided input, this input must be read to a sequence of `Symbol` objects, where a `Symbol` represents a single unit of input (i.e. a digit or operator) or a composition of these which is interpreted as a single unit (e.g. a bracketed expression).




Initial minimum backend implementation: `+`, `-`, `*` and `/` operations on integers only.
