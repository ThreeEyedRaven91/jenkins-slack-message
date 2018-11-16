def message(args) {
    def post = new URL("https://hooks.slack.com/services/${args.hookKey}").openConnection();
    def message = "payload={\"channel\": \"${args.channel}\", \"username\": \"Jenkins\", \"text\": \"${args.message}\" }";
    post.setRequestMethod("POST")
    post.setDoOutput(true)
    post.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
    post.getOutputStream().write(message.getBytes("UTF-8"));
    def postRC = post.getResponseCode();
    if(postRC.equals(200)) {
        println("[Slack] ${args.channel}: ${args.message}");
    }
}