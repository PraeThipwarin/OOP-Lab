public class Main {
    public static void main(String[] args) {
        // Create the Twitter account
        TwitterAccount account = new TwitterAccount();

        // Create followers with different reactions to different types of tweets
        Follower follower1 = new Follower("Alice", new String[] {
                "Yum! Coffee time!",  // Reaction to coffee-related tweet
                "I love coding in Java!",  // Reaction to Java-related tweet
                "Good luck on your run!"  // Reaction to running-related tweet
        });

        Follower follower2 = new Follower("Bob", new String[] {
                "I need a coffee break!",  // Reaction to coffee-related tweet
                "Java is great!",  // Reaction to Java-related tweet
                "Enjoy your run!"  // Reaction to running-related tweet
        });

        Follower follower3 = new Follower("Charlie", new String[] {
                "Coffee? Not my thing!",  // Reaction to coffee-related tweet
                "Java is overrated!",  // Reaction to Java-related tweet
                "I prefer cycling over running!"  // Reaction to running-related tweet
        });

        // Followers subscribe to the Twitter account
        account.subscribe(follower1);
        account.subscribe(follower2);
        account.subscribe(follower3);

        // Publish some tweets
        account.publishTweet("Just had the best coffee!");
        System.out.println();
        account.publishTweet("Learning Java is fun!");
        System.out.println();
        account.publishTweet("Going for a run in the park!");
    }
}