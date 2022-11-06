package polynomial;

interface ListOfTerm {
  ListOfTerm addTerm(Term newTerm);

  default boolean equalsTermNode(TermNode termNode) {
    return false;
  }

  default boolean equalsEmptyNode(EmptyNode emptyNode) {
    return false;
  }

  double evaluate(double x);

  double evaluateHelper(double x, double acc);

  int getCoefficient(int power);

  int getDegree();

  String toStringHelper(StringBuilder sb, boolean addSign);
}
