def message(args) {
    def hookKey = args.hookKey ? args.hookKey : env.TER_SLACK_HOOK_KEY
    def channel = args.channel ? args.channel : env.TER_SLACK_CHANNEL

    def post = new URL("https://hooks.slack.com/services/${hookKey}").openConnection()
    def message = "payload={\"channel\": \"${channel}\", \"username\": \"Jenkins\", \"text\": \"${args.message}\" }"

    post.setRequestMethod("POST")
    post.setDoOutput(true)
    post.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
    post.getOutputStream().write(message.getBytes("UTF-8"))
    def postRC = post.getResponseCode()

    if(postRC.equals(200)) {
        println("[Slack] ${channel}: ${args.message}")
    }
}