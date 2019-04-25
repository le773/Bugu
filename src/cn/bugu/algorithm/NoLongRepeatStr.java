package cn.bugu.algorithm;

public class NoLongRepeatStr {
    int[] statistic = new int[26];
    int maxLength = 1;
    int maxIndex = 0;

    public String getUnRepeatStr(String str) {
        char[] ch = str.toLowerCase().toCharArray();
        int length = str.length();
        int curInstance = -1;
        for (int i = 0; i < length; i++) {
            if (ch[i] > 97/* && statistic[ch[i] - 'a'] >= 0 */) {
                curInstance = i - statistic[ch[i] - 'a'];
            }
            /*
            if (ch[i] < 97*//* && statistic[ch[i] - 'A'] >= 0 *//*) {
                curInstance = i - statistic[ch[i] - 'A'];
            }
            */

            if (curInstance >= maxLength && /*statistic[ch[i] - 'A'] == -1 || */statistic[ch[i] - 'a'] != 0) {
                maxLength = curInstance;
                maxIndex = i;
            }

            if (ch[i] > 97/* && statistic[ch[i] - 'a'] >= 0 */) {
                statistic[ch[i] - 'a'] = i;
            }
            /*
            if (ch[i] < 97*//* && statistic[ch[i] - 'A'] >= 0 *//*) {
                statistic[ch[i] - 'A'] = i;
            }
            */
        }
        System.out.println("maxIndex=" + maxIndex);
        System.out.println("maxLength=" + maxLength);

        return str.substring(maxIndex - maxLength, maxIndex);
    }

    public static void main(String[] args) {
        String str = "pwwkeawabcdefgabcdefg";
        NoLongRepeatStr s = new NoLongRepeatStr();
        System.out.println(s.getUnRepeatStr(str));
    }
}
