import java.util.Random;
import javax.swing.*;

public class GuessTheNumber
{
  public static void main(String[] args)
  {
    String response;
    int range = -1;
    while(range < 2)
    {
      try
      {
        response = JOptionPane.showInputDialog("What range would you like me to pick a number in?"+
                                                               "\n\nI will pick a number between 1 and...");
        if (response == null) return;
        range = Integer.parseInt(response);
        if (range < 2) JOptionPane.showMessageDialog(null,"Please enter a number greater than 1.",
                                    "Too low.", JOptionPane.INFORMATION_MESSAGE);
      }
      catch (java.lang.NumberFormatException e)
      {
        JOptionPane.showMessageDialog(null,"Please type a number.","Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    
    int guess = -1;
    boolean firstGuess = true;
    int guessesLeft = 2;
    guessesLeft = (int)Math.ceil(new Logarithm().log2(range+1));
    int maxGuesses = guessesLeft;
    int number = new Random().nextInt(range) + 1;

    while (guessesLeft > 0)
    {
      try
      {
        if (firstGuess)
        {
          response = JOptionPane.showInputDialog("Pick a number between 1 and "+ range +".\n\n" +
                                                 "You have "+guessesLeft+" guesses, and I will tell " +
                                                 "you whether\neach guess is too low or too high.");
          
        }
        else if (guess < number)
        {
          response = JOptionPane.showInputDialog(guess + " is too low! You have "
                                                   + guessesLeft + " guesses remaining.\n\n"
                                                   + "Pick a number between 1 and "+ range +".");
        }
        else if (guess > number)
        {
          response = JOptionPane.showInputDialog(guess + " is too high! You have "
                                                   + guessesLeft + " guesses remaining.\n\n"
                                                   + "Pick a number between 1 and "+ range +".");
        }
        else
        {
          break;
        }
        if (response == null) return;
        guess = Integer.parseInt(response);
        guessesLeft--;
        firstGuess = false;
      }
      catch (java.lang.NumberFormatException e)
      {
        JOptionPane.showMessageDialog(null,"Please type a number.","Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    
    if (guess == number)
    {
      if(guessesLeft == maxGuesses - 1)
      {
        JOptionPane.showMessageDialog(null,"You guessed correctly on your first try!",
                                    "AMAZING!", JOptionPane.INFORMATION_MESSAGE);
      }
      else
      {
        JOptionPane.showMessageDialog(null,"You guessed correctly in " + (maxGuesses - guessesLeft) + " guesses!",
                                      "CORRECT!", JOptionPane.INFORMATION_MESSAGE);
      }
    }
    else
    {
      String correctNumber = Integer.toString(number);
      JOptionPane.showMessageDialog(null,"You have exceeded your maximum number of guesses. The number was "
                                      + number + ".", "WRONG!", JOptionPane.ERROR_MESSAGE);
    }
  }
}