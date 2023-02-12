import matplotlib.pyplot as pltn
import matplotlib.pyplot as plt

n = [10, 100, 1000, 10000, 100000, 1000000, 10000000]
m = [16, 260, 3758, 47900, 606622, 7137121, 82908866]
nlogn = [27.72588722,
         556.0681631,
         8231.64218,
         107768.7078,
         1331566.114,
         15780820.03,
         182332525.6]
k = [0.577078016,
     0.467568577,
     0.45653102,
     0.444470394,
     0.455570319,
     0.452265534,
     0.454712431]
plt.plot(n, m, label="Pairs (m)")
plt.plot(n, nlogn, label="nlog(n)")
plt.plot(n, [k[i] * nlogn[i] for i in range(len(n))], label="knlog(n)")
plt.xlabel("Number of Objects (n)")
plt.ylabel("Values")
plt.legend()
plt.show()
