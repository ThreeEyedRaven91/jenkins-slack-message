def message(args) {
    def post = new URL("https://hooks.slack.com/services/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").openConnection();
    def message = 'payload={"channel": "#jenkins-inno-insight", "username": "Jenkins", "text": "*InnoInsight* Build Started" }';
    post.setRequestMethod("POST")
    post.setDoOutput(true)
    post.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
    post.getOutputStream().write(message.getBytes("UTF-8"));
    def postRC = post.getResponseCode();
    println(postRC);
    if(postRC.equals(200)) {
        println(post.getInputStream().getText());
    }
}