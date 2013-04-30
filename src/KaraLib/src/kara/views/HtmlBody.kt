package kara

import java.util.*
import kara.internal.*

val <T> empty_contents: T.() -> Unit = {}

fun HTML.body(init: BODY.() -> Unit) = build(BODY(this), init)
class BODY(containingTag : HTML) : HtmlBodyTagWithText(containingTag, "body")

abstract class HtmlBodyTag(containingTag : HtmlTag?, name : String, renderStyle : RenderStyle = RenderStyle.expanded) : HtmlTag(containingTag, name, renderStyle) {
    public var id : String
        get() = this[Attributes.id]
        set(value) {
            this[Attributes.id] = value
        }
    public var c : StyleClass
        get() = this[Attributes.c]
        set(value) {
            this[Attributes.c] = value
        }
    public var style : String
        get() = this["style"]
        set(value) {
            this["style"] = value
        }

    fun style(init : StyledElement.()->Unit) {
        val element = StyledElement("inline")
        element.init()
        val builder = StringBuilder()
        for ((k, v) in element.attributes) {
            builder.append("$k:$v;")
        }

        this["style"] = builder.toString()
    }

    fun renderView(context : ActionContext, view : HtmlView) {
        view.render(context)
        children.addAll(view.children)
        view.children.clear()
    }
}


fun <T:HtmlBodyTag> HtmlBodyTag.contentTag(tag : T, c : StyleClass? = null, id : String? = null, contents : T.() -> Unit = empty_contents) {
    if (id != null) tag.id = id
    if (c != null) tag.c = c
    build(tag, contents)
}

fun HtmlBodyTag.button(c : StyleClass? = null, id : String? = null, contents : BUTTON.() -> Unit = empty_contents) = contentTag(BUTTON(this), c, id, contents)

fun HtmlBodyTag.dl(c : StyleClass? = null, id : String? = null, contents : DL.() -> Unit = empty_contents) = contentTag(DL(this), c, id, contents)
fun DL.dt(c : StyleClass? = null, id : String? = null, contents : DT.() -> Unit = empty_contents) = contentTag(DT(this), c, id, contents)
fun DL.dd(c : StyleClass? = null, id : String? = null, contents : DD.() -> Unit = empty_contents) = contentTag(DD(this), c, id, contents)

fun HtmlBodyTag.h1(c : StyleClass? = null, id : String? = null, contents : H1.() -> Unit = empty_contents) = contentTag(H1(this), c, id, contents)
fun HtmlBodyTag.h2(c : StyleClass? = null, id : String? = null, contents : H2.() -> Unit = empty_contents) = contentTag(H2(this), c, id, contents)
fun HtmlBodyTag.h3(c : StyleClass? = null, id : String? = null, contents : H3.() -> Unit = empty_contents) = contentTag(H3(this), c, id, contents)
fun HtmlBodyTag.h4(c : StyleClass? = null, id : String? = null, contents : H4.() -> Unit = empty_contents) = contentTag(H4(this), c, id, contents)
fun HtmlBodyTag.h5(c : StyleClass? = null, id : String? = null, contents : H5.() -> Unit = empty_contents) = contentTag(H5(this), c, id, contents)
fun HtmlBodyTag.img(c : StyleClass? = null, id : String? = null, contents : IMG.() -> Unit = empty_contents) = contentTag(IMG(this), c, id, contents)
fun HtmlBodyTag.input(c : StyleClass? = null, id : String? = null, contents : INPUT.() -> Unit = empty_contents) = contentTag(INPUT(this), c, id, contents)
fun HtmlBodyTag.label(c : StyleClass? = null, id : String? = null, contents : LABEL.() -> Unit = empty_contents) = contentTag(LABEL(this), c, id, contents)
fun HtmlBodyTag.select(c : StyleClass? = null, id : String? = null, contents : SELECT.() -> Unit = empty_contents) = contentTag(SELECT(this), c, id, contents)
fun HtmlBodyTag.textarea(c : StyleClass? = null, id : String? = null, contents : TEXTAREA.() -> Unit = empty_contents) = contentTag(TEXTAREA(this), c, id, contents)

fun HtmlBodyTag.a(c : StyleClass? = null, id : String? = null, contents : A.() -> Unit = empty_contents) = contentTag(A(this), c, id, contents)
open class A(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "a") {
	public var href : Link
		get() = this[Attributes.href]
		set(value) {
			this[Attributes.href] = value
		}
	public var rel : String
		get() = this[Attributes.rel]
		set(value) {
			this[Attributes.rel] = value
		}
	public var target : String
		get() = this[Attributes.target]
		set(value) {
			this[Attributes.target] = value
		}
}

open class BUTTON(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "button") {}

fun HtmlBodyTag.br(c : StyleClass? = null, id : String? = null) = contentTag(BR(this), c, id)
fun HtmlBodyTag.div(c : StyleClass? = null, id : String? = null, contents : DIV.() -> Unit = empty_contents) = contentTag(DIV(this), c, id, contents)
fun HtmlBodyTag.b(c : StyleClass? = null, id : String? = null, contents : B.() -> Unit = empty_contents) = contentTag(B(this), c, id, contents)
fun HtmlBodyTag.p(c : StyleClass? = null, id : String? = null, contents : P.() -> Unit = empty_contents) = contentTag(P(this), c, id, contents)
fun HtmlBodyTag.span(c : StyleClass? = null, id : String? = null, contents : SPAN.() -> Unit = empty_contents) = contentTag(SPAN(this), c, id, contents)
fun HtmlBodyTag.strong(c : StyleClass? = null, id : String? = null, contents : STRONG.() -> Unit = empty_contents) = contentTag(STRONG(this), c, id, contents)
fun HtmlBodyTag.small(c : StyleClass? = null, id : String? = null, contents : SMALL.() -> Unit = empty_contents) = contentTag(SMALL(this), c, id, contents)
fun HtmlBodyTag.blockquote(c : StyleClass? = null, id : String? = null, contents : BLOCKQUOTE.() -> Unit = empty_contents) = contentTag(BLOCKQUOTE(this), c, id, contents)
fun HtmlBodyTag.em(c : StyleClass? = null, id : String? = null, contents : EM.() -> Unit = empty_contents) = contentTag(EM(this), c, id, contents)

open class BR(containingTag: HtmlBodyTag) : HtmlBodyTag(containingTag, "br", RenderStyle.empty) {}
open class DIV(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "div") {}
open class B(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "b") {}
open class P(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "p") {}
open class SPAN(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "span") {}
open class STRONG(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "strong") {}
open class SMALL(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "small") {}
open class EM(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "em") {}
open class BLOCKQUOTE(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "blockquote") {
    public var cite : Link
   		get() = this[Attributes.cite]
   		set(value) {
   			this[Attributes.cite] = value
   		}
}


open class DL(containingTag: HtmlBodyTag) : HtmlBodyTag(containingTag, "dl") {}
open class DD(containingTag: DL) : HtmlBodyTagWithText(containingTag, "dd") {}
open class DT(containingTag: DL) : HtmlBodyTagWithText(containingTag, "dt") {}

abstract class ListTag(containingTag: HtmlBodyTag, name: String) : HtmlBodyTag(containingTag, name) {}
open class OL(containingTag: HtmlBodyTag) : ListTag(containingTag, "ol") {}
open class UL(containingTag: HtmlBodyTag) : ListTag(containingTag, "ul") {}
open class LI(containingTag: ListTag) : HtmlBodyTagWithText(containingTag, "li") {}
fun HtmlBodyTag.ul(c : StyleClass? = null, id : String? = null, contents : UL.() -> Unit = empty_contents) = contentTag(UL(this), c, id, contents)
fun HtmlBodyTag.ol(c : StyleClass? = null, id : String? = null, contents : OL.() -> Unit = empty_contents) = contentTag(OL(this), c, id, contents)
fun ListTag.li(c : StyleClass? = null, id : String? = null, contents : LI.() -> Unit = empty_contents) = contentTag(LI(this), c, id, contents)

open class H1(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "h1") {}
open class H2(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "h2") {}
open class H3(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "h3") {}
open class H4(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "h4") {}
open class H5(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "h5") {}
open class IMG(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "img") {
    public var width : Int
   		get() = this[Attributes.width]
   		set(value) {
   			this[Attributes.width] = value
   		}
   	public var height : Int
   		get() = this[Attributes.height]
   		set(value) {
   			this[Attributes.height] = value
   		}
   	public var src : Link
   		get() = this[Attributes.src]
   		set(value) {
   			this[Attributes.src] = value
   		}
   	public var alt : String
   		get() = this[Attributes.alt]
   		set(value) {
   			this[Attributes.alt] = value
   		}
}
open class INPUT(containingTag: HtmlBodyTag) : HtmlBodyTag(containingTag, "input") {
/*
	public var accept : String
		get() = this[Attributes.accept]
		set(value) {
			this[Attributes.accept] = value
		}
*/
	public var alt : String
		get() = this[Attributes.alt]
		set(value) {
			this[Attributes.alt] = value
		}
	public var autocomplete : Boolean
		get() = this[Attributes.autocomplete]
		set(value) {
			this[Attributes.autocomplete] = value
		}
	public var autofocus : Boolean
		get() = this[Attributes.autofocus]
		set(value) {
			this[Attributes.autofocus] = value
		}
	public var checked : Boolean
		get() = this[Attributes.checked]
		set(value) {
			this[Attributes.checked] = value
		}
	public var disabled : Boolean
		get() = this[Attributes.disabled]
		set(value) {
			this[Attributes.disabled] = value
		}
	public var height : Int
		get() = this[Attributes.height]
		set(value) {
			this[Attributes.height] = value
		}
/*
	public var list : String
		get() = this[Attributes.list]
		set(value) {
			this[Attributes.list] = value
		}
*/
/*
	public var max : String
		get() = this[Attributes.max]
		set(value) {
			this[Attributes.max] = value
		}
*/
	public var maxlength : Int
		get() = this[Attributes.maxlength]
		set(value) {
			this[Attributes.maxlength] = value
		}
/*
	public var min : String
		get() = this[Attributes.min]
		set(value) {
			this[Attributes.min] = value
		}
*/
	public var multiple : Boolean
		get() = this[Attributes.multiple]
		set(value) {
			this[Attributes.multiple] = value
		}
	public var inputType : InputType
		get() = this[Attributes.inputType]
		set(value) {
			this[Attributes.inputType] = value
		}
	public var name : String
		get() = this[Attributes.name]
		set(value) {
			this[Attributes.name] = value
		}
	public var pattern : String
		get() = this[Attributes.pattern]
		set(value) {
			this[Attributes.pattern] = value
		}
	public var placeholder : String
		get() = this[Attributes.placeholder]
		set(value) {
			this[Attributes.placeholder] = value
		}
	public var readonly : Boolean
		get() = this[Attributes.readonly]
		set(value) {
			this[Attributes.readonly] = value
		}
	public var required : Boolean
		get() = this[Attributes.required]
		set(value) {
			this[Attributes.required] = value
		}
	public var size : Int
		get() = this[Attributes.size]
		set(value) {
			this[Attributes.size] = value
		}
	public var src : Link
		get() = this[Attributes.src]
		set(value) {
			this[Attributes.src] = value
		}
	public var step : Int
		get() = this[Attributes.step]
		set(value) {
			this[Attributes.step] = value
		}
	public var value : String
		get() = this[Attributes.value]
		set(value) {
			this[Attributes.value] = value
		}
	public var width : Int
		get() = this[Attributes.width]
		set(value) {
			this[Attributes.width] = value
		}
}

abstract class TableTag(containingTag: HtmlBodyTag, name : String) : HtmlBodyTag(containingTag, name) {}
open class TABLE(containingTag: HtmlBodyTag) : TableTag(containingTag, "table") {}
open class THEAD(containingTag: TABLE) : TableTag(containingTag, "thead") {}
open class TFOOT(containingTag: TABLE) : TableTag(containingTag, "tfoot") {}
open class TBODY(containingTag: TABLE) : TableTag(containingTag, "tbody") {}
open class TR(containingTag: TableTag) : HtmlBodyTag(containingTag, "tr"){}
open class TH(containingTag: TR) : HtmlBodyTagWithText(containingTag, "th") {}
open class TD(containingTag: TR) : HtmlBodyTagWithText(containingTag, "td") {}

fun HtmlBodyTag.table(c : StyleClass? = null, id : String? = null, contents : TABLE.() -> Unit = empty_contents) = contentTag(TABLE(this), c, id, contents)
fun TABLE.tbody(c : StyleClass? = null, id : String? = null, contents : TBODY.() -> Unit = empty_contents) = contentTag(TBODY(this), c, id, contents)
fun TABLE.thead(c : StyleClass? = null, id : String? = null, contents : THEAD.() -> Unit = empty_contents) = contentTag(THEAD(this), c, id, contents)
fun TABLE.tfoot(c : StyleClass? = null, id : String? = null, contents : TFOOT.() -> Unit = empty_contents) = contentTag(TFOOT(this), c, id, contents)
fun TableTag.tr(c : StyleClass? = null, id : String? = null, contents : TR.() -> Unit = empty_contents) = contentTag(TR(this), c, id, contents)
fun TR.th(c : StyleClass? = null, id : String? = null, contents : TH.() -> Unit = empty_contents) = contentTag(TH(this), c, id, contents)
fun TR.td(c : StyleClass? = null, id : String? = null, contents : TD.() -> Unit = empty_contents) = contentTag(TD(this), c, id, contents)


fun HtmlBodyTag.form(c : StyleClass? = null, id : String? = null, contents : FORM.() -> Unit = empty_contents) = contentTag(FORM(this), c, id, contents)

fun SELECT.option(c : StyleClass? = null, id : String? = null, contents : OPTION.() -> Unit = empty_contents) = contentTag(OPTION(this), c, id, contents)
fun SELECT.optiongroup(c : StyleClass? = null, id : String? = null, contents : OPTGROUP.() -> Unit = empty_contents) = contentTag(OPTGROUP(this), c, id, contents)

open class FIELDSET(containingTag: HtmlBodyTag) : HtmlBodyTag(containingTag, "fieldset") {}
open class FORM(containingTag: HtmlBodyTag) : HtmlBodyTag(containingTag, "form") {
    public var action : Link
   		get() = this[Attributes.action]
   		set(value) {
   			this[Attributes.action] = value
   		}
   	public var enctype : EncodingType
   		get() = this[Attributes.enctype]
   		set(value) {
   			this[Attributes.enctype] = value
   		}
   	public var method : FormMethod
   		get() = this[Attributes.method]
   		set(value) {
   			this[Attributes.method] = value
   		}
}
open class SELECT(containingTag: HtmlBodyTag) : HtmlBodyTag(containingTag, "select") {
	public var name : String
		get() = this[Attributes.name]
		set(value) {
			this[Attributes.name] = value
		}
	public var size : Int
		get() = this[Attributes.size]
		set(value) {
			this[Attributes.size] = value
		}
	public var multiple : Boolean
		get() = this[Attributes.multiple]
		set(value) {
			this[Attributes.multiple] = value
		}
	public var disabled : Boolean
		get() = this[Attributes.disabled]
		set(value) {
			this[Attributes.disabled] = value
		}
}
open class OPTION(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "option") {
	public var value : String
		get() = this[Attributes.value]
		set(value) {
			this[Attributes.value] = value
		}
	public var label : String
		get() = this[Attributes.label]
		set(value) {
			this[Attributes.label] = value
		}
	public var disabled : Boolean
		get() = this[Attributes.disabled]
		set(value) {
			this[Attributes.disabled] = value
		}

    public var selected : Boolean
        get() = this[Attributes.selected]
        set(value) {
            this[Attributes.selected] = value
        }
}
open class OPTGROUP(containingTag: HtmlBodyTag) : HtmlBodyTag(containingTag, "optgroup") {}

open class TEXTAREA(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "textarea") {
	public var autofocus : Boolean
		get() = this[Attributes.autofocus]
		set(value) {
			this[Attributes.autofocus] = value
		}
	public var cols : Int
		get() = this[Attributes.cols]
		set(value) {
			this[Attributes.cols] = value
		}
	public var disabled : Boolean
		get() = this[Attributes.disabled]
		set(value) {
			this[Attributes.disabled] = value
		}
	public var maxlength : Int
		get() = this[Attributes.maxlength]
		set(value) {
			this[Attributes.maxlength] = value
		}
	public var name : String
		get() = this[Attributes.name]
		set(value) {
			this[Attributes.name] = value
		}
	public var placeholder : String
		get() = this[Attributes.placeholder]
		set(value) {
			this[Attributes.placeholder] = value
		}
	public var readonly : Boolean
		get() = this[Attributes.readonly]
		set(value) {
			this[Attributes.readonly] = value
		}
	public var required : Boolean
		get() = this[Attributes.required]
		set(value) {
			this[Attributes.required] = value
		}
	public var rows : Int
		get() = this[Attributes.rows]
		set(value) {
			this[Attributes.rows] = value
		}
	public var wrap : Wrap
		get() = this[Attributes.wrap]
		set(value) {
			this[Attributes.wrap] = value
		}
}

fun HtmlBodyTag.fieldset(c : StyleClass? = null, id : String? = null, contents : FIELDSET.() -> Unit = empty_contents) = contentTag(FIELDSET(this), c, id, contents)
fun FIELDSET.legend(c : StyleClass? = null, id : String? = null, contents : LEGEND.() -> Unit = empty_contents) = contentTag(LEGEND(this), c, id, contents)

open class LABEL(containingTag: HtmlBodyTag) : HtmlBodyTagWithText(containingTag, "label") {
    public var forId : String
   		get() = this[Attributes.forId]
   		set(value) {
   			this[Attributes.forId] = value
   		}
}
open class LEGEND(containingTag: FIELDSET) : HtmlBodyTagWithText(containingTag, "legend") {}

fun HtmlBodyTag.canvas(c : StyleClass? = null, id : String? = null, contents : CANVAS.() -> Unit = empty_contents) = contentTag(CANVAS(this), c, id, contents)
open class CANVAS(containingTag: HtmlBodyTag) : HtmlBodyTag(containingTag, "canvas") {
    public var width : Int
   		get() = this[Attributes.width]
   		set(value) {
   			this[Attributes.width] = value
   		}
   	public var height : Int
   		get() = this[Attributes.height]
   		set(value) {
   			this[Attributes.height] = value
   		}
}