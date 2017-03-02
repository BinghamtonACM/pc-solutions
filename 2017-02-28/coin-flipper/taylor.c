#include <stdio.h>
#include <string.h>
#include <stdlib.h>

static const char *COINS[8] = {
  "TTT",
  "TTH",
  "THT",
  "THH",
  "HTT",
  "HTH",
  "HHT",
  "HHH",
};

static int *KEYS;

void logs(char *s) {
  fputs(s, stderr);
  fputs("\n", stderr);
}

void init_keys() {
  int keys_size = 8;
  int *keys = malloc(sizeof(int)*keys_size);
  for (int i = 0; i < 8; i++) {
    int k = COINS[i][0] + COINS[i][1]*2 + COINS[i][2]*4;
    if (k >= keys_size) {
      // Like a give a darn
      keys_size = k + 1;
      keys = realloc(keys, sizeof(int)*keys_size);
    }
    keys[k] = i;
  }
  KEYS = keys;
}

void read_case(char *seq) {
  for (int i = 0; i < 38; i++) {
    seq[i] = getc(stdin);
  }
}

void solve(int c, char *seq) {
  int tally[8] = {0};
  for (int i = 0; i < 38; i++) {
    int k = seq[i] + seq[i+1]*2 + seq[i+2]*4;
    tally[KEYS[k]]++;
  }
  printf("%d %d %d %d %d %d %d %d %d\n",
	 c, tally[0], tally[1], tally[2], tally[3], tally[4], tally[5], tally[6], tally[7]);
}

int main() {
  init_keys();
  char seq[64];
  int n, c;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d", &c);
    scanf("%s", seq);
    solve(c, seq);
  }
     
  return 0;
}
