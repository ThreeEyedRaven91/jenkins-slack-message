import groovy.json.*

def message(args) {
    def hookKey = args.hookKey ? args.hookKey : env.TER_SLACK_HOOK_KEY
    def channel = args.channel ? args.channel : env.TER_SLACK_CHANNEL
    def color = args.color

    def payload = [
        channel: channel,
        username: "Jenkins",
        attachments: [
            [
                author_name: "Jenkins",
                title: args.message,
                title_link: args.title_link,
                color: color,
                fields: args.fields
            ]
        ]
    ]
    def payloadString = JsonOutput.toJson(payload);

    def post = new URL("https://hooks.slack.com/services/${hookKey}").openConnection()
    def message = "payload=${payloadString}"

    post.setRequestMethod("POST")
    post.setDoOutput(true)
    post.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
    post.getOutputStream().write(message.getBytes("UTF-8"))
    def postRC = post.getResponseCode()

    if(postRC.equals(200)) {
        println("[Slack] ${channel}: ${args.message}")
    }
}

def infoMessage(args) {
    if (env.PROJECT_NAME) {
        args.fields.add([
            title: "Project",
            value: env.PROJECT_NAME,
            short: true
        ])
    }
    args.fields.add([
        title: "Author",
        value: env.CHANGE_AUTHOR,
        short: true
    ])
    args.fields.add([
        title: "Merge from",
        value: env.CHANGE_BRANCH,
        short: true
    ])
    args.fields.add([
        title: "Message",
        value: env.CHANGE_TITLE,
        short: true
    ])

    if (env.JOB_DISPLAY_URL) {
        args.title_link = env.JOB_DISPLAY_URL;
    }

    message(args)
}