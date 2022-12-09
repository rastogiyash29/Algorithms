package String;
public class Z_algorithm{

    // ____******____Z-Function____******____
	// for each index i in str.len, it gives us the length of longest
	// substring starting at ith index and is proper prefix
	static int[] Z_function(String S) {
		char[] s = S.toCharArray();
		int n = s.length;
		int[] z = new int[n];

		z[0] = s.length; // { No proper prefix possible from here }

		int l = 0; // blue range shaded in figure
		int r = 0;

		for (int i = 0; i < n; i++) {
			// Giving a KickStart from calculated answers
			if (i <= r) {
				z[i] = Math.min(r - i + 1, z[i - l]);
			}

			// Finding complete answer brute force way
			while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
				z[i]++;
			}

			// Updating BLUE range
			if (i + z[i] - 1 > r) {
				l = i;
				r = i + z[i] - 1;
			}
		}
		return z;
	}

    public static void main(String[] args) {
        String s="ababadababae";
        int[] z=Z_function(s);
        for(int e:z)System.out.print(e+" ");
    }
}