import java.util.HashSet;
import java.util.Set;

interface Observer<E> {
    void notify(E event);
}
interface Observable<E> {
    void subscribe(Observer<E> observer);
}

public class TwitterAccount implements Observable<String> {
    private Set<Observer<String>> subscribers = new HashSet<>();

    public void subscribe(Observer<String> follower) {
        subscribers.add(follower);
    }

    public void publishTweet(String tweet) {
        System.out.println("Post: " + tweet);
        for (Observer<String> subscriber : subscribers)
            subscriber.notify(tweet);
    }
}

class Follower implements Observer<String> {
    private String name;
    private String[] reactions;

    public Follower(String name, String[] reactions) {
        this.name = name;
        this.reactions = reactions;
    }

    @Override
    public void notify(String tweet) {
        String reaction = generateReaction(tweet);
        System.out.println(name + " reacts to tweet: " + reaction);
    }

    private String generateReaction(String tweet) {
        // For simplicity, match the reaction to specific keywords in the tweet
        if (tweet.contains("coffee")) {
            return reactions[0];  // Reaction for coffee-related tweets
        } else if (tweet.contains("Java")) {
            return reactions[1];  // Reaction for Java-related tweets
        } else if (tweet.contains("run")) {
            return reactions[2];  // Reaction for running-related tweets
        } else {
            return "Hmm, interesting...";  // Default reaction
        }
    }
}
