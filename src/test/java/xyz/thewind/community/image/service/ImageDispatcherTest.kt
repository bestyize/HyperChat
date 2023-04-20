package xyz.thewind.community.image.service

import org.junit.jupiter.api.Test
import xyz.thewind.community.image.model.ImageSrc
import xyz.thewind.music.util.GsonUtil


class ImageDispatcherTest {

    @Test
    fun testKonaChan() {
        val result = ImageDispatcher.search("", 1, 0, ImageSrc.KONACHAN.src)
        print(GsonUtil.toJson(result))

    }

    @Test
    fun testWallHaven() {
        val result = ImageDispatcher.search("", 1, 0, ImageSrc.WALLHAVEN.src)
        print(GsonUtil.toJson(result))
    }

}