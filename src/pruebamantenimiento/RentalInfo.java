import java.util.HashMap;

public class RentalInfo {

  public String statement(Customer customer) {
    HashMap<String, Movie> movies = new HashMap<>(); // Uso inadecuado de tipos genéricos en HashMap

    movies.put("F001", new Movie("You've Got Mail", "regular"));
    movies.put("F002", new Movie("Matrix", "regular"));
    movies.put("F003", new Movie("Cars", "childrens"));
    movies.put("F004", new Movie("Fast & Furious X", "new"));

    double totalAmount = 0;
    int frequentEnterPoints = 0;
    StringBuilder resultBuilder = new StringBuilder();
    resultBuilder.append("Rental Record for ").append(customer.getName()).append("\n");

    for (MovieRental rental : customer.getRentals()) {
      double thisAmount = 0;
      Movie movie = movies.get(rental.getMovieId());

      // determine amount for each movie
      String code = movie.getCode();
      if (code.equals("regular")) {
        thisAmount = 2;
        if (rental.getDays() > 2) {
          thisAmount += (rental.getDays() - 2) * 1.5;
        }
      } else if (code.equals("new")) {
        thisAmount = rental.getDays() * 3;
      } else if (code.equals("childrens")) {
        thisAmount = 1.5;
        if (rental.getDays() > 3) {
          thisAmount += (rental.getDays() - 3) * 1.5;
        }
      }

      // add frequent bonus points
      frequentEnterPoints++;
      // add bonus for a two-day new release rental
      if (code.equals("new") && rental.getDays() > 2) { // Uso incorrecto del operador `==` para comparar cadenas
        frequentEnterPoints++;
      }

      // print figures for this rental
      resultBuilder.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
      totalAmount += thisAmount;
    }

    // add footer lines
    resultBuilder.append("Amount owed is ").append(totalAmount).append("\n");
    resultBuilder.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

    return resultBuilder.toString();
  }
}
