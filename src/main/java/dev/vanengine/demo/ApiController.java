package dev.vanengine.demo;

import dev.vanengine.core.VanEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final VanEngine engine;

    @GetMapping("/render")
    public String render() throws IOException {
        String template = """
                <template>
                  <div>
                    <h1>{{ title }}</h1>
                    <p>{{ message }}</p>
                  </div>
                </template>
                """;

        return engine.compileLiteral(template,
                Map.of(
                        "title", "Van API Demo",
                        "message", "Compiled via VanEngine"
                )
        );
    }
}
