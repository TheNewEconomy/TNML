# TheNewMenuLibrary

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/a4759b6313274de3b27108c0de5987fd)](https://www.codacy.com/gh/TheNewEconomy/TNML/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=TheNewEconomy/TNML&amp;utm_campaign=Badge_Grade)
[![Build Status](https://ci.codemc.io/job/creatorfromhell/job/TheNewMenuLibrary/badge/icon)](https://ci.codemc.io/job/creatorfromhell/job/TheNewMenuLibrary/)

The New Menu Library is a versatile tool designed for developers working within The New Economy ecosystem, enabling the creation of custom, inventory-based menus that function seamlessly across multiple Minecraft platforms. This library allows developers to craft their menus just once and deploy them effortlessly on various supported platforms, streamlining the development process and ensuring consistency in user experience.

It's important to note that The New Menu Library relies on The New Item Library as a dependency, so developers should confirm that their target platform is compatible with both libraries. By leveraging this library, developers can efficiently manage and implement custom menus, enhancing their Minecraft projects with ease and flexibility.

## Documentation

[Wiki](https://github.com/TheNewEconomy/TNML/wiki/)

[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://theneweconomy.github.io/TNML/javadoc/)


## Maven
The New Menu Library uses maven for dependency management.

Repository:
```XML
<repository>
    <id>codemc-releases</id>
    <url>https://repo.codemc.io/repository/maven-public/</url>
</repository>
```

Core Dependency:
```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-CORE</artifactId>
    <version>1.7.0.0-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

Bukkit Dependency:
```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-Bukkit</artifactId>
    <version>1.7.0.0-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

Folia Dependency:
```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-Folia</artifactId>
    <version>1.7.0.0-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

Sponge API Version 7 Dependency:

*Currently not supported because need to add support in TNIL first*

```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-Sponge-API-7</artifactId>
    <version>1.7.0.0-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

Sponge API Version 8+ Dependency:

```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-Sponge-API-8</artifactId>
    <version>1.7.0.0-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

## Contributing

Contributions to TheNewEconomy are welcome and encouraged! Whether you're fixing a bug, adding a new feature, or improving documentation, we would love your help.

However, to ensure the project stays consistent and manageable, we ask that you follow our [contributing guidelines](.contributing/contributing.md) before submitting a pull request.

Please make sure to:

- Sign the Contributor License Agreement (CLA) if this is your first contribution when it appears in the Pull Request.
- Follow the coding standards and branch naming conventions outlined in the guidelines.
- Use the required IntelliJ plugin **Final Obsession** for code quality and consistency.

Thank you for your contributions!
