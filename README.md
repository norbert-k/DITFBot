# DITFBot
RTU DITF Discord Bot

### Contributing

If you want to contribute to DITFBot, make sure to base your branch off of our development branch and create your PR into that same branch.

```
1) Create your own branch from development brach
    1.1) Name should be equal to Feature Request name
    
2) Push changes to newly created branch
3) Create merge request to development branch and request code-review for merge request.
```

### Dependencies

This project requires Java 15+. (`openjdk`, `adoptopenjdk`)

* JDA
    * Gradle: `compile 'net.dv8tion:JDA:4.2.0_205'`
    * Version: 4.2.0_*

* Logback
    * Gradle: `compile 'ch.qos.logback:logback-classic:1.2.3'`
    * Version: 1.2.3
    
* JCommander
    * Gradle: `compile "com.beust:jcommander:1.71"`
    * Version: 1.71

* JUnit
    * Gradle: `testCompile group: 'junit', name: 'junit', version: '4.12'`
    * Version: 4.12

Dependencies are defined in `build.gradle` file and should be automatically downloaded when building project.

### Related projects & documentation:

* JDA:
    * Github: https://github.com/DV8FromTheWorld/JDA
    * Documentation
        * Wiki: https://github.com/DV8FromTheWorld/JDA/wiki
        * Javadoc: https://ci.dv8tion.net/job/JDA/javadoc/

* JCommander:
    * Github: https://github.com/cbeust/jcommander
    * Documentation: https://jcommander.org

### Roadmap

- [x] Read config.properties file for discord bot specific information
- [ ] CLI Interface with JCommander
- [ ] Message handling architecture
- [ ] Generate an invitation link from CLI & print it out on Bot startup
- [ ] List all joined guilds on startup and while running
- [ ] Log every message with logger
- [ ] Ping <-> Pong message handler
- [ ] System information (!debuginfo) message handler (GC, CPU usage etc...)
- [ ] Extra message handler (custom messages, further review required!)
- [ ] Database access (read configuration from config.properties)
- [ ] Database migrations with flyway
- [ ] Log every message to database
- [ ] Simple VueJS based message viewer

### Feature list

#### CLI / Startup:
- [ ] Read config.properties config file for API keys & DB settings
- [ ] Interactive CLI with tab completion and --help support
- [ ] Pre startup health-checks

#### Integrations
- [ ] RTU Sudent Calendar (`.ics`) support
- [ ] Register users with RTU OAuth
- [ ] Message logging 

#### Quality control
- [ ] Tests
- [ ] Benchmarks
- [ ] Documentation

#### Ease of use
- [ ] Docker image
- [ ] Helm image
- [ ] `.deb`, `.rpm` etc.. packages
