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
                text: args.message,
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