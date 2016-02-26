import java.util.HashMap;

/**
 * Created by Vivek on 2/27/2016.
 */
class AlgoUtility
{

    /**
     * recursive function who split with AND and maintain brackets positions
     * @param value
     * @param search
     * @param queryContext
     * @return : return string by replacing JEval expression by splitting with AND and
     * if found OR then call OR recursion and take values from it too.
     */
    static String splitAND(String value, String search, HashMap<String, Object> queryContext)
    {
        String[] andTokens = value.split("( AND )");

        for(int index=0; index<andTokens.length; index++)
        {

            if(!value.contains(AlgoConstants.OR) && search.length()>0)
            {
                if(!search.endsWith("|| "))
                {
                    search += AlgoConstants.AND_OPERATOR;
                }
            }

            if(value.contains(AlgoConstants.OR))
            {
                search = splitOR(value, search, queryContext);
            }
            else
            {
                search = checkStartBracketOrEndBracket(andTokens, index, search, queryContext);
            }
        }

        return search;
    }

    /**
     * recursive function who split with AND and maintain brackets positions
     * @param value
     * @param search
     * @param queryContext
     * @return : return string by replacing JEval expression by splitting with OR and
     * if found AND then call AND recursion and take values from it too.
     */
    static String splitOR(String value, String search, HashMap<String, Object> queryContext)
    {
        String[] orTokens = value.split("( OR )");

        for(int index = 0 ; index< orTokens.length;index++)
        {

            if(!orTokens[index].contains(AlgoConstants.AND) && search.length()>0)
            {
                if(!search.endsWith("&& "))
                {
                    search+=AlgoConstants.OR_OPERATOR;
                }
            }

            if(orTokens[index].contains(AlgoConstants.AND))
            {
                search += splitAND(orTokens[index], search, queryContext);
            }
            else
            {
                search = checkStartBracketOrEndBracket(orTokens, index, search, queryContext);
            }
        }

        return search;
    }

    /**
     * recursive function who check bracket starting and ending positions and create expression as per brackets
     * @param tokens
     * @param index
     * @param search
     * @param context
     * @return : return string with proper starting end Ending brackets positions but as per JEval expression requires.
     */
    static String checkStartBracketOrEndBracket(String[] tokens, int index, String search, HashMap<String, Object> context)
    {

        search = startsWithBracket(tokens, index, search);

        context.put(AlgoConstants.END_BRACKET, "");

        String token = endsWithBracket(tokens[index], context);

        search += "indexOf('" +context.get(AlgoConstants.MATCH_STRING) + "','"+token+"',0)!=-1";

        search += context.get(AlgoConstants.END_BRACKET).toString();

        return search;
    }

    /**
     * recursive function who check bracket starting positions and create expression as per brackets
     * @param tokens
     * @param index
     * @param search
     * @return : return string with proper starting brackets positions but as per JEval expression requires.
     */
    static String startsWithBracket(String[] tokens, int index, String search)
    {
        if(tokens[index].trim().startsWith("("))
        {
            search+="(";

            tokens[index] = tokens[index].trim().substring(1,tokens[index].length());

            search = startsWithBracket(tokens, index, search);
        }
        return  search;
    }

    /**
     * recursive function who check bracket ending positions and create expression as per brackets
     * @param search
     * @param context
     * @return : return string with proper Ending brackets positions but as per JEval expression requires.
     */
    static String endsWithBracket(String search, HashMap<String, Object> context)
    {
        String bracketsString = context.get(AlgoConstants.END_BRACKET).toString();

        if(search.trim().endsWith(")"))
        {
            bracketsString+=")";

            context.put(AlgoConstants.END_BRACKET, bracketsString);

            search = search.trim().substring(0,search.length()-1);

            search = endsWithBracket(search, context);
        }

        return  search;
    }
}
