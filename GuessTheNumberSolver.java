import javax.swing.*;

public class GuessTheNumberSolver
{
  public static void main(String[] args)
  {
    int range = -1;
    String response;
    while(range < 2)
    {
      try
      {
        response = JOptionPane.showInputDialog("What range would you like to pick a number in?\n\n"+
                                                                 "You will pick a number between 1 and...");
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
    
    int guess = (int)Math.round(range/2.0);
    double trueGuessModifier = guess/2.0;
    int guessModifier = (int)Math.round(trueGuessModifier);
    int guessesLeft = 2;
    guessesLeft = (int)Math.ceil(new Logarithm().log2(range+1));
    int maxGuesses = guessesLeft;
    int answer = JOptionPane.showConfirmDialog(null,"My first guess is "+ guess +
                                               " and\nI should be able to guess it in no more than " + 
                                               maxGuesses +" guesses.\n\nIs "+ guess +" correct?",
                                             "First Guess", JOptionPane.YES_NO_OPTION);
    guessesLeft--;
    int highLow = 0;
    Object[] buttons = {"Too high", "Too low"};
    while (answer != 0 && guessesLeft > 0)
    {
      if (answer == -1) return;
      highLow = JOptionPane.showOptionDialog(null, "Was my guess too high or too low?", "High or Low",
                                             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                             null, buttons, buttons[0]);
      if (highLow == -1) return;
      if (highLow == 0) guess = guess - guessModifier;
      else guess = guess + guessModifier;
      
      answer = JOptionPane.showConfirmDialog(null,"My next guess is "+ guess +".\n\nIs "+ guess +" correct?",
                                             "Guess #"+(maxGuesses - guessesLeft + 1), JOptionPane.YES_NO_OPTION);
      guessesLeft--;
      if (guessModifier > 1)
      {
        trueGuessModifier = trueGuessModifier/2.0;
        guessModifier = (int)Math.round(trueGuessModifier);
      }
    }
    
    if (answer == 0)
    {
      if (guessesLeft != maxGuesses - 1)
      {
        JOptionPane.showMessageDialog(null,"I guessed correctly in " + (maxGuesses - guessesLeft) + " guesses!",
                                      "CORRECT!", JOptionPane.INFORMATION_MESSAGE);
      }
      else
      {
        JOptionPane.showMessageDialog(null,"I guessed correctly on my first try!",
                                    "I am too good!", JOptionPane.INFORMATION_MESSAGE);
      }
    }
    else
    {
      JOptionPane.showMessageDialog(null,"I have failed!", "WRONG!", JOptionPane.ERROR_MESSAGE);
    }
  }
}