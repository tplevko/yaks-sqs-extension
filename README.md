# YAKS SQS Extension
This is sqs-extension for yaks framework, which can be used as jitpack module when writing tests

## How to use it

You can fork this repository on github or create your very own repository holding your custom step implementations. Once you have that in place you need to add
a [JitPack](https://jitpack.io/) configuration as well as the runtime dependency pointing to your custom step repository into the `yaks-settings.yaml` in your YAKS project.

_yaks-settings.yaml_
```yaml
repositories:
  - repository:
      id: "central"
      name: "Maven Central"
      url: "https://repo.maven.apache.org/maven2/"
      releases:
        enabled: "true"
        updatePolicy: "daily"
      snapshots:
        enabled: "false"
  - repository:
      id: "jitpack.io"
      name: "JitPack Repository"
      url: "https://jitpack.io"
dependencies:
  - dependency:
      groupId: {github.user}
      artifactId: {repo}
      version: {tag}
```

The `groupId` is your github user name, the `artifactId` the name of the repository and the version either a valid `tag` or master branch.

With this configuration you can run your YAKS test and load the custom steps extension using JitPack:

```bash
$ yaks test custom-steps.feature -s yaks-settings.yaml
```
