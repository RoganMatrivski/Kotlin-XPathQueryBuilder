package com.github.roganmatrivski.xpathquerybuilder

object Operator {
    private fun compBase(compOp: String, arg1: IExpression, arg2: IExpression, vararg argN: IExpression): XPathExpression {
        val strAppend = if (argN.isEmpty()) {
            ""
        } else {
            " $compOp ${argN.joinToString(" $compOp ") { expr -> expr.render() }}"
        }

        return XPathExpression { "${arg1.render()} $compOp ${arg2.render()}${strAppend}" }
    }

    fun and(arg1: IExpression, arg2: IExpression, vararg argN: IExpression) =
        compBase("and", arg1, arg2, *argN)

    fun or(arg1: IExpression, arg2: IExpression, vararg argN: IExpression) =
        compBase("or", arg1, arg2, *argN)

    fun not(arg: IExpression) =
        XPathExpression {"not(${arg.render()})"}

    fun add(arg1: IExpression, arg2: IExpression, vararg argN: IExpression) =
        compBase("+", arg1, arg2, *argN)

    fun sub(arg1: IExpression, arg2: IExpression, vararg argN: IExpression) =
        compBase("-", arg1, arg2, *argN)

    fun mult(arg1: IExpression, arg2: IExpression, vararg argN: IExpression) =
        compBase("-", arg1, arg2, *argN)

    fun div(arg1: IExpression, arg2: IExpression, vararg argN: IExpression) =
        compBase("div", arg1, arg2, *argN)

    fun union(arg1: IExpression, arg2: IExpression, vararg argN: IExpression) =
        compBase("|", arg1, arg2, *argN)

    fun parenthesis(arg: IExpression) =
        XPathExpression {"(${arg.render()})"}

    fun equal(arg1: IExpression, arg2: IExpression) =
        compBase("=", arg1, arg2)

    fun not_equal(arg1: IExpression, arg2: IExpression) =
        compBase("!=", arg1, arg2)

    fun greater_than(arg1: IExpression, arg2: IExpression) =
        compBase(">", arg1, arg2)

    fun lesser_than(arg1: IExpression, arg2: IExpression) =
        compBase("<", arg1, arg2)

    fun greater_than_equal(arg1: IExpression, arg2: IExpression) =
        compBase(">=", arg1, arg2)

    fun lesser_than_equal(arg1: IExpression, arg2: IExpression) =
        compBase("<=", arg1, arg2)

    fun mod(arg1: IExpression, arg2: IExpression) =
        compBase("mod", arg1, arg2)

    fun contains(lhs: IExpression, rhs: IExpression) =
        XPathExpression {"contains(${lhs.render()}, ${rhs.render()})"}
}