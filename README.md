# TheNewMenuLibrary

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/a4759b6313274de3b27108c0de5987fd)](https://www.codacy.com/gh/TheNewEconomy/TNML/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=TheNewEconomy/TNML&amp;utm_campaign=Badge_Grade)
[![Build Status](https://ci.codemc.io/job/creatorfromhell/job/TheNewMenuLibrary/badge/icon)](https://ci.codemc.io/job/creatorfromhell/job/TheNewMenuLibrary/)

A library used by The New Economy for custom inventory-based menus. The New Menu Library grants developers
the ability to write their custom menus once, and run it across multiple Minecraft platforms. Check below for
platform support. Please note this library depends on The New Item Library so verify that the platform is supported
there too.

## Documentation

[Wiki](https://github.com/TheNewEconomy/TNML/wiki/)

[JavaDoc]() - coming soon

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
    <version>1.5.0.1-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

Bukkit Dependency:
```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-Bukkit</artifactId>
    <version>1.5.0.1-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

Folia Dependency:
```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-Folia</artifactId>
    <version>1.5.0.1-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

Sponge API Version 7 Dependency:

```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-Sponge-API-7</artifactId>
    <version>1.5.0.1-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```

Sponge API Version 8+ Dependency:

*Currently not supported because lack of documentation on Sponge's End*

```XML
<dependency>
    <groupId>net.tnemc</groupId>
    <artifactId>TNML-Sponge-API-8</artifactId>
    <version>1.5.0.1-SNAPSHOT-2</version>
    <scope>compile</scope>
</dependency>
```
