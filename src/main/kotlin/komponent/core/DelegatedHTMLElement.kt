package komponent.core

import org.w3c.dom.Element
import org.w3c.dom.GeometryUtils
import org.w3c.dom.HTMLCollection
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLSlotElement
import org.w3c.dom.NodeList
import org.w3c.dom.css.CSSStyleDeclaration
import org.w3c.dom.events.Event

open class DelegatedHTMLElement(private val delegate: dynamic) : HTMLElement(), GeometryUtils by (delegate as GeometryUtils) {

    override var onpaste: ((Event) -> dynamic)?
        get() = delegate.onpaste as ((Event) -> dynamic)?
        set(value) { delegate.onpaste = value }
    override var onpause: ((Event) -> dynamic)?
        get() = delegate.onpause as ((Event) -> dynamic)?
        set(value) { delegate.onpause = value }
    override var onplay: ((Event) -> dynamic)?
        get() = delegate.onplay as ((Event) -> dynamic)?
        set(value) { delegate.onplay = value }
    override var onplaying: ((Event) -> dynamic)?
        get() = delegate.onplaying as ((Event) -> dynamic)?
        set(value) { delegate.onplaying = value }
    override var onprogress: ((Event) -> dynamic)?
        get() = delegate.onprogress as ((Event) -> dynamic)?
        set(value) { delegate.onprogress = value }
    override var onratechange: ((Event) -> dynamic)?
        get() = delegate.onratechange as ((Event) -> dynamic)?
        set(value) { delegate.onratechange = value }
    override var onreset: ((Event) -> dynamic)?
        get() = delegate.onreset as ((Event) -> dynamic)?
        set(value) { delegate.onreset = value }
    override var onresize: ((Event) -> dynamic)?
        get() = delegate.onresize as ((Event) -> dynamic)?
        set(value) { delegate.onresize = value }
    override var onscroll: ((Event) -> dynamic)?
        get() = delegate.onscroll as ((Event) -> dynamic)?
        set(value) { delegate.onscroll = value }
    override var onseeked: ((Event) -> dynamic)?
        get() = delegate.onseeked as ((Event) -> dynamic)?
        set(value) { delegate.onseeked = value }
    override var onseeking: ((Event) -> dynamic)?
        get() = delegate.onseeking as ((Event) -> dynamic)?
        set(value) { delegate.onseeking = value }
    override var onselect: ((Event) -> dynamic)?
        get() = delegate.onselect as ((Event) -> dynamic)?
        set(value) { delegate.onselect = value }
    override var onshow: ((Event) -> dynamic)?
        get() = delegate.onshow as ((Event) -> dynamic)?
        set(value) { delegate.onshow = value }
    override var onstalled: ((Event) -> dynamic)?
        get() = delegate.onstalled as ((Event) -> dynamic)?
        set(value) { delegate.onstalled = value }
    override var onsubmit: ((Event) -> dynamic)?
        get() = delegate.onsubmit as ((Event) -> dynamic)?
        set(value) { delegate.onsubmit = value }
    override var onsuspend: ((Event) -> dynamic)?
        get() = delegate.onsuspend as ((Event) -> dynamic)?
        set(value) { delegate.onsuspend = value }
    override var ontimeupdate: ((Event) -> dynamic)?
        get() = delegate.ontimeupdate as ((Event) -> dynamic)?
        set(value) { delegate.ontimeupdate = value }
    override var ontoggle: ((Event) -> dynamic)?
        get() = delegate.ontoggle as ((Event) -> dynamic)?
        set(value) { delegate.ontoggle = value }
    override var onvolumechange: ((Event) -> dynamic)?
        get() = delegate.onvolumechange as ((Event) -> dynamic)?
        set(value) { delegate.onvolumechange = value }
    override var onwaiting: ((Event) -> dynamic)?
        get() = delegate.onwaiting as ((Event) -> dynamic)?
        set(value) { delegate.onwaiting = value }
    override var onwheel: ((Event) -> dynamic)?
        get() = delegate.onwheel as ((Event) -> dynamic)?
        set(value) { delegate.onwheel = value }
    override val previousElementSibling: Element?
        get() = delegate.previousElementSibling as Element?
    override val style: CSSStyleDeclaration
        get() = delegate.style as CSSStyleDeclaration

    override var oncopy: ((Event) -> dynamic)?
        get() = delegate.oncopy as ((Event) -> dynamic)?
        set(value) { delegate.oncopy = value }
    override var oncut: ((Event) -> dynamic)?
        get() = delegate.oncut as ((Event) -> dynamic)?
        set(value) { delegate.oncut = value }
    override var contentEditable: String
        get() = delegate.contentEditable as String
        set(value) { delegate.contentEditable = value }
    override val isContentEditable: Boolean
        get() = delegate.isContentEditable as Boolean

    override var onabort: ((Event) -> dynamic)?
        get() = delegate.onabort as ((Event) -> dynamic)?
        set(value) { delegate.onabort = value }
    override var onblur: ((Event) -> dynamic)?
        get() = delegate.onblur as ((Event) -> dynamic)?
        set(value) { delegate.onblur = value }
    override var oncancel: ((Event) -> dynamic)?
        get() = delegate.oncancel as ((Event) -> dynamic)?
        set(value) { delegate.oncancel = value }
    override var oncanplay: ((Event) -> dynamic)?
        get() = delegate.oncanplay as ((Event) -> dynamic)?
        set(value) { delegate.oncanplay = value }
    override var oncanplaythrough: ((Event) -> dynamic)?
        get() = delegate.oncanplaythrough as ((Event) -> dynamic)?
        set(value) { delegate.oncanplaythrough = value }
    override var onchange: ((Event) -> dynamic)?
        get() = delegate.onchange as ((Event) -> dynamic)?
        set(value) { delegate.onchange = value }
    override var onclick: ((Event) -> dynamic)?
        get() = delegate.onclick as ((Event) -> dynamic)?
        set(value) { delegate.onclick = value }
    override var onclose: ((Event) -> dynamic)?
        get() = delegate.onclose as ((Event) -> dynamic)?
        set(value) { delegate.onclose = value }
    override var oncontextmenu: ((Event) -> dynamic)?
        get() = delegate.oncontextmenu as ((Event) -> dynamic)?
        set(value) { delegate.oncontextmenu = value }
    override var oncuechange: ((Event) -> dynamic)?
        get() = delegate.oncuechange as ((Event) -> dynamic)?
        set(value) { delegate.oncuechange = value }
    override var ondblclick: ((Event) -> dynamic)?
        get() = delegate.ondblclick as ((Event) -> dynamic)?
        set(value) { delegate.ondblclick = value }
    override var ondrag: ((Event) -> dynamic)?
        get() = delegate.ondrag as ((Event) -> dynamic)?
        set(value) { delegate.ondrag = value }
    override var ondragend: ((Event) -> dynamic)?
        get() = delegate.ondragend as ((Event) -> dynamic)?
        set(value) { delegate.ondragend = value }
    override var ondragenter: ((Event) -> dynamic)?
        get() = delegate.ondragenter as ((Event) -> dynamic)?
        set(value) { delegate.ondragenter = value }
    override var ondragexit: ((Event) -> dynamic)?
        get() = delegate.ondragexit as ((Event) -> dynamic)?
        set(value) { delegate.ondragexit = value }
    override var ondragleave: ((Event) -> dynamic)?
        get() = delegate.ondragleave as ((Event) -> dynamic)?
        set(value) { delegate.ondragleave = value }
    override var ondragover: ((Event) -> dynamic)?
        get() = delegate.ondragover as ((Event) -> dynamic)?
        set(value) { delegate.ondragover = value }
    override var ondragstart: ((Event) -> dynamic)?
        get() = delegate.ondragstart as ((Event) -> dynamic)?
        set(value) { delegate.ondragstart = value }
    override var ondrop: ((Event) -> dynamic)?
        get() = delegate.ondrop as ((Event) -> dynamic)?
        set(value) { delegate.ondrop = value }
    override var ondurationchange: ((Event) -> dynamic)?
        get() = delegate.ondurationchange as ((Event) -> dynamic)?
        set(value) { delegate.ondurationchange = value }
    override var onemptied: ((Event) -> dynamic)?
        get() = delegate.onemptied as ((Event) -> dynamic)?
        set(value) { delegate.onemptied = value }
    override var onended: ((Event) -> dynamic)?
        get() = delegate.onended as ((Event) -> dynamic)?
        set(value) { delegate.onended = value }
    override var onerror: ((dynamic, String, Int, Int, Any?) -> dynamic)?
        get() = delegate.onerror as ((dynamic, String, Int, Int, Any?) -> dynamic)?
        set(value) { delegate.onerror = value }
    override var onfocus: ((Event) -> dynamic)?
        get() = delegate.onfocus as ((Event) -> dynamic)?
        set(value) { delegate.onfocus = value }
    override var oninput: ((Event) -> dynamic)?
        get() = delegate.oninput as ((Event) -> dynamic)?
        set(value) { delegate.oninput = value }
    override var oninvalid: ((Event) -> dynamic)?
        get() = delegate.oninvalid as ((Event) -> dynamic)?
        set(value) { delegate.oninvalid = value }
    override var onkeydown: ((Event) -> dynamic)?
        get() = delegate.onkeydown as ((Event) -> dynamic)?
        set(value) { delegate.onkeydown = value }
    override var onkeypress: ((Event) -> dynamic)?
        get() = delegate.onkeypress as ((Event) -> dynamic)?
        set(value) { delegate.onkeypress = value }
    override var onkeyup: ((Event) -> dynamic)?
        get() = delegate.onkeyup as ((Event) -> dynamic)?
        set(value) { delegate.onkeyup = value }
    override var onload: ((Event) -> dynamic)?
        get() = delegate.onload as ((Event) -> dynamic)?
        set(value) { delegate.onload = value }
    override var onloadeddata: ((Event) -> dynamic)?
        get() = delegate.onloadeddata as ((Event) -> dynamic)?
        set(value) { delegate.onloadeddata = value }
    override var onloadedmetadata: ((Event) -> dynamic)?
        get() = delegate.onloadedmetadata as ((Event) -> dynamic)?
        set(value) { delegate.onloadedmetadata = value }
    override var onloadend: ((Event) -> dynamic)?
        get() = delegate.onloadend as ((Event) -> dynamic)?
        set(value) { delegate.onloadend = value }
    override var onloadstart: ((Event) -> dynamic)?
        get() = delegate.onloadstart as ((Event) -> dynamic)?
        set(value) { delegate.onloadstart = value }
    override var onmousedown: ((Event) -> dynamic)?
        get() = delegate.onmousedown as ((Event) -> dynamic)?
        set(value) { delegate.onmousedown = value }
    override var onmouseenter: ((Event) -> dynamic)?
        get() = delegate.onmouseenter as ((Event) -> dynamic)?
        set(value) { delegate.onmouseenter = value }
    override var onmouseleave: ((Event) -> dynamic)?
        get() = delegate.onmouseleave as ((Event) -> dynamic)?
        set(value) { delegate.onmouseleave = value }
    override var onmousemove: ((Event) -> dynamic)?
        get() = delegate.onmousemove as ((Event) -> dynamic)?
        set(value) { delegate.onmousemove = value }
    override var onmouseout: ((Event) -> dynamic)?
        get() = delegate.onmouseout as ((Event) -> dynamic)?
        set(value) { delegate.onmouseout = value }
    override var onmouseover: ((Event) -> dynamic)?
        get() = delegate.onmouseover as ((Event) -> dynamic)?
        set(value) { delegate.onmouseover = value }
    override var onmouseup: ((Event) -> dynamic)?
        get() = delegate.onmouseup as ((Event) -> dynamic)?
        set(value) { delegate.onmouseup = value }
    override val nextElementSibling: Element?
        get() = delegate.nextElementSibling as Element?
    override val childElementCount: Int
        get() = delegate.childElementCount as Int
    override val children: HTMLCollection
        get() = delegate.children as HTMLCollection
    override val firstElementChild: Element?
        get() = delegate.firstElementChild as Element?
    override val lastElementChild: Element?
        get() = delegate.lastElementChild as Element?
    override val assignedSlot: HTMLSlotElement?
        get() = delegate.assignedSlot as HTMLSlotElement?

    override fun append(vararg nodes: dynamic) {
        delegate.append(nodes)
    }

    override fun prepend(vararg nodes: dynamic) {
        delegate.prepend(nodes)
    }

    override fun querySelector(selectors: String): Element? {
        return delegate.querySelector(selectors) as Element?
    }

    override fun querySelectorAll(selectors: String): NodeList {
        return delegate.querySelectorAll(selectors) as NodeList
    }

    override fun after(vararg nodes: dynamic) {
        delegate.after(nodes)
    }

    override fun remove() {
        delegate.remove()
    }

    override fun replaceWith(vararg nodes: dynamic) {
        delegate.replaceWith(nodes)
    }

    override fun before(vararg nodes: dynamic) {
        delegate.before(nodes)
    }
}