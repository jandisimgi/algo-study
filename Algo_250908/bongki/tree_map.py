n = int(input())
arr = [input() for _ in range(n)]

from sortedcontainers import SortedDict
sd= SortedDict()

for t in arr:
    if t not in sd:
        sd[t] = 0
        sd[t] += 1
    else:
        sd[t] += 1

for k,v in sd.items():
    percent = (v / n) * 100
    print(f"{k} {percent:.4f}")
