package Leetcode.p355_DesignTwitter;

import java.util.*;

class Twitter {

    static int timeStamp = 1;
    class Tweet {
        int tweetId;
        Tweet next;
        int time;

        Tweet(int tweetId) {
            this.tweetId = tweetId;
            this.time = timeStamp++;
        }
    }

    class User {
        Set<User> followed;
        Tweet head;

        User() {
            followed = new HashSet<>();
            head = null;
        }

        void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId);
            tweet.next = head;
            head = tweet;
        }

        void follow(User followee) {
            followed.add(followee);
        }

        void unfollow(User followee) {
            if(followed.contains(followee)) {
                followed.remove(followee);
            }
        }
    }

    Map<Integer, User> map;
    /** Initialize your data structure here. */
    public Twitter() {
        map = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        map.putIfAbsent(userId, new User());
        User user = map.get(userId);
        user.post(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        if(!map.containsKey(userId)) {
            return result;
        }

        PriorityQueue<Tweet> heap = new PriorityQueue<>((a, b) -> (b.time - a.time));

        User user = map.get(userId);
        Set<User> followed_list = user.followed;

        for(User followed: followed_list) {
            if(followed.head != null) {
                heap.offer(followed.head);
            }
        }
        if(user.head != null) {
            heap.offer(user.head);
        }

        int k = 10;
        while(!heap.isEmpty() && k > 0) {
            Tweet tHead = heap.poll();
            if(tHead.next != null) {
                heap.offer(tHead.next);
            }
            result.add(tHead.tweetId);

            k--;
        }

        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followeeId == followeeId) {
            return;
        }
        map.putIfAbsent(followerId, new User());
        map.putIfAbsent(followeeId, new User());

        User follower = map.get(followerId);
        User followee = map.get(followeeId);
        follower.follow(followee);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followeeId == followeeId) {
            return;
        }
        if(!map.containsKey(followerId) || !map.containsKey(followeeId)) {
            return;
        }

        User follower = map.get(followerId);
        User followee = map.get(followeeId);
        follower.unfollow(followee);
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1,5);
//        System.out.println(twitter.getNewsFeed(1));

        twitter.follow(1, 1);
        System.out.println(twitter.getNewsFeed(1));

    }
}
