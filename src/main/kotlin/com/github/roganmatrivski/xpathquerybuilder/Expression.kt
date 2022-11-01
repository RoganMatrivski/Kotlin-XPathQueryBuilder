package com.github.roganmatrivski.xpathquerybuilder

class XPathExpression(val argRender: () -> String = {""}): IExpression {
    override fun render(): String = argRender()

    private val getPrev = run {
        val prevEl = render()
        if (prevEl.isEmpty()) "" else "$prevEl/"
    }

    fun ancestor(tag: String = "*") =
        XPathExpression { "${getPrev}ancestor::$tag" }

    fun ancestor_or_self(tag: String = "*") =
        XPathExpression { "${getPrev}ancestor-or-self::$tag" }

    fun descendant(tag: String = "*") =
        XPathExpression { "${getPrev}descendant::$tag" }

    fun descendant_or_self(tag: String = "*") =
        XPathExpression { "${getPrev}descendant-or-self::$tag" }

    fun self(tag: String = "*") =
        XPathExpression { "${getPrev}self::$tag" }

    fun parent(tag: String = "*") =
        XPathExpression { "${getPrev}parent::$tag" }

    fun following(tag: String = "*") =
        XPathExpression { "${getPrev}following::$tag" }

    fun following_sibling(tag: String = "*") =
        XPathExpression { "${getPrev}following-sibling::$tag" }

    fun preceding(tag: String = "*") =
        XPathExpression { "${getPrev}following::$tag" }

    fun preceding_sibling(tag: String = "*") =
        XPathExpression { "${getPrev}preceding-sibling::$tag" }

    fun where(xpath: IExpression) =
        XPathExpression { "${render()}[${xpath.render()}]" }

    fun attribute(attr: String) =
        XPathExpression { "${getPrev}attribute::$attr" }

    fun idAttr() =
        attribute("id")

    fun classAttr() =
        attribute("class")

    fun name() =
        XPathExpression { "name(${render()})" }

    fun text() =
        XPathExpression { "${getPrev}text()" }

    fun equal(value: String) =
        XPathExpression { "${render()} = '$value'" }

    fun not_equal(value: String) =
        XPathExpression { "${render()} != '$value'" }

    fun greater_than(value: String) =
        XPathExpression { "${render()} > '$value'" }

    fun lesser_than(value: String) =
        XPathExpression { "${render()} < '$value'" }

    fun greater_than_equal(value: String) =
        XPathExpression { "${render()} >= '$value'" }

    fun lesser_than_equal(value: String) =
        XPathExpression { "${render()} <= '$value'" }

    fun partial_equal(value: String) =
        XPathExpression { "contains(${render()}, '$value')" }

    fun fromAnywhere(tag: String = "*") =
        XPathExpression { "//$tag" }

//    fun fromRoot(tag: String = "*") =
//        XPathExpression { "/$tag" }

    fun any() =
        XPathExpression { "${getPrev}*" }

    fun self() =
        XPathExpression { "${getPrev}self::node" }

    fun node(tag: String) =
        XPathExpression { "${getPrev}$tag" }
}