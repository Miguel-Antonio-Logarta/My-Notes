import numpy
import torch
from typing import List
import timeit
import matplotlib.pyplot as plt
from functools import partial

def multiply_matrices(A: List[List[int]], B: List[List[int]]) -> List[List[int]]:
    # If A is an m x n matrix and B is a n x p matrix,
    # Our resulting matrix C should be of size m x p

    # Check if dimensions match (n)
    if len(A[0]) != len(B):
        print("Shapes of matrices A and B do not agree!")
        return
    
    m = len(A)
    p = len(B[0])
    n = len(B)

    # Initiailize array
    # C = zero_matrix(m, p)
    C = numpy.zeros((m, p))

    for i, crow in enumerate(C):
        for j, ccolumn in enumerate(crow):
            ABSum = 0
            for k in range(n):
                ABSum += A[i][k]*B[k][j]
            
            C[i][j] = ABSum

    return C


if __name__ == "__main__":
    m_values = [10, 20, 30, 40, 50, 60, 70, 80, 90, 100]
    plain_time_data = {}
    torch_time_data = {}

    for m in m_values:
        W = numpy.random.rand(90, m)
        X = numpy.random.rand(m, 110)

        W_t = torch.tensor(W)
        X_t = torch.tensor(X)

        # Plain python function with for loops
        plain_time = timeit.Timer(partial(multiply_matrices, W, X)).repeat(1, 1)
        # plain_time_data[m] = float(plain_time[0])

        # Pytorch matrices multiplication
        torch_time = timeit.Timer(partial(torch.matmul, W_t, X_t)).repeat(1, 1)

        # torch_time_data[m] = float(torch_time[0])

        # Mane I dont have any idea how to plot any of this using matplotlib
        # plt.plot(m, plain_time, "plain")
        # plt.plot(m, torch_time, "torch")

        
        # matplotlib.pyplot
        # print(plain_time_data)
        # print(torch_time_data)
        # plt.plot('size of m', 'execution time', data=plain_time_data)
        # plt.plot('size of m', 'execution time', data=torch_time_data)

        # matplotlib.