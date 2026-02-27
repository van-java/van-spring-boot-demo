# van-spring-boot-demo

<p>
  <a href="https://github.com/vanengine/van"><img src="https://img.shields.io/badge/van-template%20engine-steelblue" alt="Van" /></a>
  <a href="../../../LICENSE"><img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="License" /></a>
</p>

一个最小化的 Spring Boot 示例应用，演示 [van-spring-boot-starter](https://github.com/van-java/van-spring-boot-starter) 的集成方式。

<p>
  <a href="../../../README.md">English</a> · <a href="README.md">简体中文</a>
</p>

## 运行

```bash
./gradlew bootRun
```

打开 [http://localhost:8080](http://localhost:8080)。

## 接口

| 接口 | 类型 | 说明 |
|---|---|---|
| `GET /` | MVC 视图 | 使用 Spring Model 渲染 `themes/default/pages/index.van` |
| `GET /api/render` | REST API | 通过 `VanEngine.compileLiteral()` 编译内联模板 |

## 项目结构

```
src/main/
├── java/dev/vanengine/demo/
│   ├── DemoApplication.java      # Spring Boot 启动入口
│   ├── IndexController.java      # MVC 控制器 → 视图名 "index"
│   └── ApiController.java        # REST 控制器 → 内联模板
└── resources/
    ├── application.yml            # server.port: 8080
    └── themes/default/pages/
        └── index.van              # 包含 {{ title }} 和 {{ message }} 的模板
```

## 工作原理

### MVC 视图 (IndexController)

```java
@GetMapping("/")
public String index(Model model) {
    model.addAttribute("title", "Van Demo");
    model.addAttribute("message", "Hello from Van + Spring Boot!");
    return "index";  // 解析为 themes/default/pages/index.van
}
```

starter 自动配置的 `VanViewResolver` 会编译 `index.van` 并将 `{{ expr }}` 占位符替换为模型数据。

### REST API (ApiController)

```java
@GetMapping("/api/render")
public String render() throws IOException {
    return engine.compileLiteral(template,
            Map.of("title", "Van API Demo", "message", "Compiled via VanEngine"));
}
```

直接使用 `VanEngine` — 编译内联模板并一步到位完成渲染。

## 相关项目

- [**Van**](https://github.com/vanengine/van) — 核心模板引擎（Rust / WASM）
- [**van-java-core**](https://github.com/van-java/van-java-core) — 纯 Java SDK
- [**van-spring-boot-starter**](https://github.com/van-java/van-spring-boot-starter) — Spring Boot 集成

## 许可证

[MIT](../../../LICENSE)
