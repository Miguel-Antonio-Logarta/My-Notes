import torch
from torch.utils.data import Dataset, DataLoader
import torch.nn.functional as F
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Our MLP Model
class MLP(torch.nn.Module):
    def __init__(self, num_features, num_classes):
        super().__init__()

        self.all_layers = torch.nn.Sequential(
            # 1st hidden layer
            torch.nn.Linear(num_features, 50),
            torch.nn.Sigmoid(),
            # torch.nn.ReLU(),

            # 2nd hidden layer
            torch.nn.Linear(50, 100),
            torch.nn.Sigmoid(),
            # torch.nn.ReLU(),

            # output layer
            torch.nn.Linear(100, num_classes),
        )

    def forward(self, x):
        logits = self.all_layers(x)
        return logits


# Data loader
class MyDataset(Dataset):
    def __init__(self, X, y):
        self.features = torch.tensor(X, dtype=torch.float32)
        self.features = self.features.view(-1,1) # Turn this into a 2D tensor with 1 column

        self.labels = torch.tensor(y, dtype=torch.float32)
        self.labels = self.labels.view(-1, 1)

    def __getitem__(self, index):
        x = self.features[index]
        y = self.labels[index]        
        return x, y

    def __len__(self):
        return self.labels.shape[0]


def plot_original_data(X, y):
    plt.title("Multi Layer Perception (MLP)")
    plt.scatter(X, y, s=3, label="Original Data")
    plt.xlabel("x")
    plt.ylabel("y")


def plot_decision_boundary(X, y, X_train, model, step=0.01):
    # Get data points
    X_range = torch.arange(X_train.min(), X_train.max(), step).view(-1, 1)
    y_pred = model(torch.tensor(X_range, dtype=torch.float32)).detach().numpy()
    
    X_mean = X.mean(axis=0)
    X_std = X.std(axis=0)
    y_mean = y.mean(axis=0)
    y_std = y.std(axis=0)

    # Denormalize
    X_range = (X_range*X_std)+X_mean
    y_pred = (y_pred*y_std)+y_mean

    plt.plot(X_range, y_pred, color='orange', label='Regression Curve', linewidth=3)


def plot_training_loss(loss):
    plt.title("Training Loss")
    plt.xlabel("Epochs")
    plt.ylabel("Loss")
    plt.plot(range(1, len(loss) + 1), loss, label="Training Loss")
    plt.show()


if __name__ == "__main__":
    # Load the data from csv file
    df = pd.read_csv('mlp_regression_data.csv')

    # Convert to numpy arrays
    X = df["x"].values # Features (Only 1 feature)
    y = df["y"].values # Labels (Only 1 class)

    # Set our training data
    X_train = X
    y_train = y

    # Normalize training data
    X_mean = X_train.mean(axis=0)
    X_std = X_train.std(axis=0)
    y_mean = y_train.mean(axis=0)
    y_std = y_train.std(axis=0)

    X_train = (X_train - X_mean) / X_std
    y_train = (y_train - y_mean) / y_std
    
    # Load training data
    train_ds = MyDataset(X_train, y_train)

    # Load dataset into dataloader
    train_loader = DataLoader(
        dataset=train_ds,
        batch_size=32,
        shuffle=True,
    )

    # Begin training loop
    torch.manual_seed(123)
    model = MLP(num_features=1, num_classes=1) # 1 input neuron 1 output neuron
    optimizer = torch.optim.SGD(model.parameters(), lr=0.1) # Stochastic gradient descent

    num_epochs = 200
    losses = []

    for epoch in range(num_epochs):
        running_loss = 0.0
        model = model.train()
        for batch_idx, (features, labels) in enumerate(train_loader):
            
            # Forward pass
            logits = model(features)
            loss = F.mse_loss(logits, labels) # Loss function
            
            # Backwards pass
            optimizer.zero_grad()
            loss.backward()

            # Update model parameters
            optimizer.step()

            # Log the loss
            running_loss += loss.item()
            print(f'Epoch: {epoch+1:03d}/{num_epochs:03d}'
                f' | Batch {batch_idx+1:03d}/{len(train_loader):03d}'
                f' | Loss: {loss:.2f}')
        
        # Divide the total running loss of the epoch by the size of the batch
        losses.append(running_loss / len(train_loader))
        
    # Plot training results
    plot_original_data(X, y)
    plot_decision_boundary(X, y, X_train, model, step=0.01)
    plt.show()

    # Plot the training loss
    plot_training_loss(losses)
