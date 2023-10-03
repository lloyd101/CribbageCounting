//import java.util.*;
//import java.util.stream.*;

public class Counter {
    PowerSet<Card> cardps;// set of all possible subsets of the hand
    Card starter;

    public Counter(Card[] hand, Card starter) {
        this.starter = starter;
        this.cardps = new PowerSet<>(hand);
    }

    public int countPoints() {
        int finalPoints = 0;
        // counts the points from pairs, fifteens, flush, nobs, and runs
        finalPoints += countPairs() + countFifteens() + countFlush() + countNobs() + countRuns();
        return finalPoints;
    }

    // count the number of runs and returns the total points
    private int countRuns() {
        int runPoints = 0;
        int n = cardps.getLength(); // length of the PowerSet of the hand
        Set<Card> longestRun = null;
        Set<Card> currSet;
        // find the longest run in the PowerSet
        for (int c = 0; c < n; c++) {
            currSet = cardps.getSet(c);
            if (currSet != null) {
                if (longestRun == null || (isRun(currSet) && currSet.getLength() > longestRun.getLength())) {
                    longestRun = currSet;
                }
            }
        }
        // count the number of runs of the same length as the longest run
        for (int j = 0; j < cardps.getLength(); j++) {
            currSet = cardps.getSet(j);
            if (currSet != null && longestRun.getLength() == currSet.getLength() && isRun(currSet)) {
                runPoints++;
            }
        }

        // return the total points for runs
        return runPoints * longestRun.getLength();
    }

    // check if a set of cards represents a possible run
    private boolean isRun (Set<Card> set) {
        // In this method, we are going through the given set to check if it constitutes a run of 3 or more
        // consecutive cards. To do this, we are going to create an array of 13 cells to represent the
        // range of card ranks from 1 to 13. We go through each card and increment the cell corresponding to
        // each card's rank. For example, an Ace (rank 1) would cause the first (index 0) cell to increment.
        // An 8 would cause the 8th (index 7) cell to increment. When this loop is done, the array will
        // contain 5 or less cells with values of 1 or more to represent the number of cards with each rank.
        // Then we can use this array to search for 3 or more consecutive non-zero values to represent a run.

        int n = set.getLength();

        if (n <= 2) return false; // Run must be at least 3 in length.

        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.

        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank()-1] += 1;
        }

        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak) maxStreak = streak;
            } else {
                streak = 0;
            }
        }
        if (maxStreak == n) { // Check if this is the maximum streak.
            return true;
        } else {
            return false;
        }

    }

    // This method counts the number of combinations of cards that add up to 15
    private int countFifteens() {
        int fPoints = 0;
        Set<Card> currSet;
        for (int i = 0; i < cardps.getLength(); i++) {
            currSet = cardps.getSet(i);
            if (currSet != null) {
                int totalTemp = 0;
                int tempCounter = currSet.getLength();
                for (int a = 0; a < tempCounter; a++) {
                    totalTemp += currSet.getElement(a).getFifteenRank();
                }
                if (totalTemp == 15) fPoints++;
            }
        }

        return fPoints * 2;
    }

    // This method counts the number of pairs of cards with the same label
    private int countPairs() {
        int pairPoints = 0;
        Set<Card> currentSet;
        for (int i = 0; i < cardps.getLength(); i++) {
            currentSet = cardps.getSet(i);
            if (currentSet != null && currentSet.getLength() == 2
                    && currentSet.getElement(0).getLabel().equals(currentSet.getElement(1).getLabel())) {
                pairPoints++;
            }
        }

        return pairPoints * 2;
    }

    // This method checks if there is a flush in the hand
    private int countFlush() {
        int flushPoints = 0;
        String suit = null;
        Set currSet;

        boolean allSameSuit = true;

        for (int i = 0; i < cardps.getLength(); i++) {
            if (cardps.getSet(i) != null) {
                currSet = cardps.getSet(i);

                if (currSet.getLength() == 4 && (!currSet.contains(starter))) {

                    for (int c = 0; c < currSet.getLength(); c = c + 1) {


                        if (suit == null) {
                            suit = ((Card) (currSet.getElement(c))).getSuit();
                        }
                        if (suit.equals(((Card) (currSet.getElement(c))).getSuit())) {

                            allSameSuit = true;


                        } else {
                            allSameSuit = false;
                            break;
                        }
                    }
                }
            }
        }
        if(allSameSuit ) {
            if (suit.equals(starter.getSuit())) {
                flushPoints=4+1;
            }


            else {
                flushPoints=4;
            }
        }
        return flushPoints;


    }

    // This method checks if there is a Jack of the same suit as the starter card
    private int countNobs() {
        int Npoints = 0;
        Set currRun = null;

        for (int x = 0; x < cardps.getLength(); x+=1) {

            if(cardps.getSet(x)!=null) {

                currRun = cardps.getSet(x);
                for (int c = 0; c < currRun.getLength(); c = c +1) {

                    if (((Card) currRun.getElement(c)).getLabel().equals("J") && ((Card) currRun.getElement(c)).getSuit().equals(starter.getSuit())){
                        Npoints=1;
                        return Npoints;
                    }
                }
            }

        }
        return Npoints;
    }

}
