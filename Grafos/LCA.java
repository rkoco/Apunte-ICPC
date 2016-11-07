class LCA{
	int MAX = 20;
	int[][] P = new int[N][MAX]; //P[i][0] parent de i, P[root][0] = root 
	int[] level = new int[N]; //level[root] = 0

	public int dist(int A, int B){
		int C = lca(A,B);
		lastlca = C;
		return level[A] + level[B] - 2*level[C];
	}
	public int anc_dist(int A, int dist) {
		for (int i = 0; i < MAX; i++) {
			if (((1 << i) & dist) != 0) {
				A = P[A][i];
			}
		}
		return A;
	}
	public int lca(int A, int B) {
		if (level[A] < level[B]){
			int aux = A;
			A = B;
			B = aux;
		}
		int dif = level[A] - level[B];
		A = anc_dist(A, dif);
		if (A == B){
			return A;
		}
		for (int k = MAX-1; k >= 0; --k) {
			if (P[A][k] != P[B][k]) {
				A = P[A][k];
				B = P[B][k];
			}
		}
		return P[A][0];
	}
	public void init_lca() {
		for (int k = 1; k < MAX; ++k)
			for (int i = 0; i < N; ++i)
				P[i][k] = P[P[i][k-1]][k-1];
	}
	public int anc_level(int A, int l) {
		for (int k = MAX-1; k >= 0; --k) {
			if (level[P[A][k]] >= l)
				A = P[A][k];
		}
		return A;
	}
}