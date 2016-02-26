import net.sourceforge.jeval.Evaluator;

/**
 * Created by Vivek on 2/27/2016.
 */
public class BasicTestWithJEval
{

    private static String query = "(((Microsoft AND Windows) OR Server) AND (2008 OR R2) OR Enterprise)";

    private static String messageCase = "Enterprise".replace("\'", "").replace(",", "");

    private static AlgoExecuter algoExecuter = new AlgoExecuter(messageCase, query);

    private static Evaluator evaluator = new Evaluator();

    public static void main(String[] args)
    {

        //Test
        try {

            String evaluateExpression = algoExecuter.execute();

            boolean isQueryParsed = evaluator.getBooleanResult(evaluateExpression);

            System.out.println("jEval Query : "+evaluateExpression);

            System.out.println("Parsed in jEval : "+isQueryParsed);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
