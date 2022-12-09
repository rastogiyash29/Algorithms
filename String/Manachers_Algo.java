package String;
public class Manachers_Algo {

    // APPROACH 4 : Manchers Algorithm-- Used to find longgest palindromic substring in O(N)
	public static String longestPalindrome(String S) {
		// Step 1: Lets modify the given String and use Hash between each character to
		// take care of even length palindromes as each hash will act as space between
		// each pair of adjacent characters. and append @ and & as start and end
		// characters.

		StringBuffer str = new StringBuffer();
		str.append("@#");
		for (int i = 0; i < S.length(); i++) {
			str.append(S.charAt(i));
			str.append('#');
		}
		str.append('&');

		// assigning the modified string to char array for easy computation and creating
		// the equal length answer array which will contain the max length palindrome
		// from that center.
		char[] s = str.toString().toCharArray();
		int n = s.length;
		int[] ans = new int[n];

		// creating C as Center variable, R as right most index upto which we have
		// available
		// some pre-computed result.
		int C = 0, R = 0;

		// Step 2 : here lets start finding answer for each centre.
		for (int i = 1; i < n - 1; i++) { // starting with 1 because for start char '@' answer will be 0.

			// Mancher's Part
			int mirror = 2 * C - i; // as mirror = C-(i-C); as eg: {A B} (D) {B A} <- (i-C) part
			if (i < R) { // if i is in boundary of precomputed results.
				ans[i] = Math.min(ans[mirror], R - i); // max feasible answer. known till now
			}

			// brute force {extending palindrome to both left and right side}
			while (s[i + ans[i] + 1] == s[i - ans[i] - 1]) {
				ans[i]++;
			}

			// updating boundaries if needed.
			if (i + ans[i] > R) {
				C = i;
				R = i + ans[i];
			}
		}

		// Step 3 : Here picking the max possible answer and returning it.
		int maxLen = 1;
		int st = 0;
		for (int i = 2; i < n; i++) {
			if (ans[i] > maxLen) {
				maxLen = ans[i];
				if (i % 2 == 0) { // odd length palindrome with a center element
					st = (i / 2) - 1 - maxLen / 2;
				} else { // even length palindrome with # as center.
					st = (i / 2) - 1 - (maxLen - 1) / 2;
				}
			}
		}

		return S.substring(st, st + maxLen);
	}

    public static void main(String[] args) {
        String s="ababbabacdcee";
        String longestPalin=longestPalindrome(s);
        System.out.println(longestPalin);   
    }
}
