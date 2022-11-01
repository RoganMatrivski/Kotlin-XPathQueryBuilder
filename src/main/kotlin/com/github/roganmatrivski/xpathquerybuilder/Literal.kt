package com.github.roganmatrivski.xpathquerybuilder

class Literal(val str: String): IExpression {
    override fun render() = str

}