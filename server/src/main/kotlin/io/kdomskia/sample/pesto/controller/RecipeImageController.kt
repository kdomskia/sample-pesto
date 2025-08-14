package io.kdomskia.sample.pesto.controller

import java.io.IOException
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/recipe")
class RecipeImageController {

    @GetMapping("/{recipeId}/image")
    fun findImage(@PathVariable recipeId: String): ResponseEntity<InputStreamResource> {
        try {
            val resource = ClassPathResource("image/recipe/$recipeId.webp")
            return ResponseEntity
                .status(HttpStatus.OK)
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("image/webp"))
                .body(InputStreamResource(resource.inputStream))
        } catch (e: IOException) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Recipe or image not found"
            )
        }
    }

}