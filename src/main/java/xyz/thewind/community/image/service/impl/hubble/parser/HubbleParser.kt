package xyz.thewind.community.image.service.impl.hubble.parser

import xyz.thewind.community.image.model.ImageInfo
import xyz.thewind.global.util.HttpUtil
import xyz.thewind.music.util.GsonUtil

object HubbleParser {

    fun search(keyword: String, page: Int, num: Int): MutableList<ImageInfo>{
        val list = mutableListOf<ImageInfo>()

        try {
            // https://esahubble.org/images/archive/category/galaxies/page/2/
            val api = "https://esahubble.org/images/viewall/page/$page/"
            val content = HttpUtil.get(api)
            val data = "{\"list\":["+content.substringAfter("var images = [")
                .substringBefore("];")
                .replace("        ", "")
                .replace("    ", "")
                .replace("\n", "").replace("", "")
                .replace("',", "\",")
                .replace("id: '", "\"id\": \"")
                .replace("width: ", "\"width\": ")
                .replace("height: ", "\"height\": ")
                .replace("title: '", "\"title\": \"")
                .replace("src: '", "\"src\": \"")
                .replace("url: '", "\"url\": \"")
                .replace("potw: '", "\"potw\": \"")
                .replace("'}", "\"}")+ "]}"
            val datas: HubbleResponse = GsonUtil.fromJson(data, HubbleResponse::class.java)

            datas.list.forEach {
                // https://cdn.spacetelescope.org/archives/images/thumb300y/heic2007a.jpg
                // https://cdn.spacetelescope.org/archives/images/screen/heic1501a.jpg
                // https://cdn.spacetelescope.org/archives/images/large/heic1501a.jpg
                // https://esahubble.org/media/archives/images/original/heic1501a.tif
                val previewImageUrl = it.src?.replace("thumb300y", "screen")
                val imageUrl = it.src?.replace("thumb300y", "large")
                list.add(ImageInfo().apply {
                    this.id = it.id
                    this.tags = it.title
                    this.width = it.width
                    this.height = it.height
                    this.pageUrl = "https://esahubble.org" + it.url
                    this.previewImageUrl = previewImageUrl
                    this.imageUrl = imageUrl
                    this.originImageUrl = "https://esahubble.org/media/archives/images/original/${it.id}.tif"

                })
            }
        } catch (_: Exception){}

        return list
    }

}