import matplotlib.pyplot as plt

# Data
data = {'Ordered': [0.06802912, 0.01425169, 0.02840876, 0.05748545, 0.08569629, 0.07252716],
        'Partially Ordered': [0.10170917, 0.05872581, 0.19919457, 0.7102762, 2.26819255, 9.14479587],
        'Random Ordered': [0.02816503, 0.07106128, 0.25910789, 0.67066835, 3.00083332, 11.9918242],
        'Reverse Ordered': [0.08838754, 0.07620836, 0.25561085, 0.93678499, 3.44194499, 20.5362225]}

input_size = [1000, 2000, 4000, 8000, 16000, 32000]

# Plotting
plt.figure(figsize=(10, 6))
for key in data.keys():
    plt.plot(input_size, data[key], label=key)

# Labels and legend
plt.ylabel('Input size (N)')
plt.xlabel('Time (ms)')
plt.legend()

# Show plot
plt.show()
