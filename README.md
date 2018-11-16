# jenkins-slack-message

Jenkins library provide quick method to send to slack

## Installation and use
### Add to Jenkins

Go to `Manage Jenkins` => `Configure System` => `Global Pipeline Libraries
` => Press `Add`

Fill the following information:

| Information        | Value                                                     |
|--------------------|-----------------------------------------------------------|
| Name               | slack-message                                             |
| Default version    | master                                                    |
| Retrieval method   | Modern SCM                                                |
| Project Repository | git@github.com:ThreeEyedRaven91/jenkins-slack-message.git |
| Credentials        | none                                                      |

### Add to Jenkins file

Top of Jenkins file

```
@Library('slack-message') _
```

Config Env if you want

```
pipeline {
    ...
    environment {
        TER_SLACK_HOOK_KEY = '<hookKey>'
        TER_SLACK_CHANNEL = '<channel>'
    }
    ...
}
```

Use inside the steps

```
steps {
    script {
        terSlack.message(message: "<some message that you want>")
    }
}
```

If you don't define the env

```
steps {
    script {
        terSlack.message(
            message: "<some message that you want>",
            channel: "<channel>",
            hookKey: "<hookKey>"
        )
    }
}
```

### Accepted input

| Key     | Global Env         | Description                                                           |
|---------|--------------------|-----------------------------------------------------------------------|
| message |                    |  Message sent to channel                                              |
| channel | TER_SLACK_CHANNEL  | Channel name, include `#`                                             |
| hookKey | TER_SLACK_HOOK_KEY | HookKey, usually find in `https://hooks.slack.com/services/<hookKey>` |