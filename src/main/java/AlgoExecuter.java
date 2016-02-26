import java.util.HashMap;

/**
 * Created by Vivek on 2/27/2016.
 */
public class AlgoExecuter
{
    private String jEvalExpression = "";

    private HashMap<String, Object> context = new HashMap<String, Object>();

    /**
     * constructor which takes message to match and query to create JEval expression
     * @param baseToMatch
     * @param query
     */
    public AlgoExecuter(String baseToMatch, String query){
        context.put(AlgoConstants.QUERY, query);
        context.put(AlgoConstants.MATCH_STRING, baseToMatch);
    }
    private AlgoExecuter(){

    }


    /**
     * Take message to match and query from context which was done from constructor
     * @return : string of JEval expressions from given query and message.
     */
    public String execute() throws Exception
    {
        String[] orTokens = context.get(AlgoConstants.QUERY).toString().split("( OR )");

        for(int index = 0 ; index< orTokens.length;index++)
        {
            if(index > 0)
            {
                jEvalExpression +=AlgoConstants.OR_OPERATOR;
            }

            if(orTokens[index].contains(AlgoConstants.AND))
            {
                jEvalExpression = AlgoUtility.splitAND(orTokens[index], jEvalExpression, context);
            }
            else
            {
                jEvalExpression = AlgoUtility.checkStartBracketOrEndBracket(orTokens, index, jEvalExpression, context);
            }
        }
        return jEvalExpression.trim()!=null ? jEvalExpression : null;
    }
}
