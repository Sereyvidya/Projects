import numpy as np
from utils import find_nearest_neighbors
# from mutation import mutate_genes, read_unmutated_genes, read_complete_genes
from distance_matrix import calculate_distance_matrix, rebuild_distance_matrix
from Bio import Phylo
import matplotlib.pyplot as plt

def calculate_phylo_tree(cluster_names, distance_matrix):
    """
    Use the WPGMA algorithm to calculate a phylogenetic tree.

    Parameters:
    distance_matrix (NumPy.array): The distance matrix as a numpy 2D array.
    cluster_names (list): The names of organisms/clusters.

    Returns:
    (str): A phylogenetic tree in newick format.
    """
    newick_tree = ""        # Initialize empty string for the newick format of a biopython phylo tree
    name_dict = {}          # Dictionary for storing names of clusters
    length_dict = {}        # Dictionary for storing length of clusters

    if len(distance_matrix) <= 1:
        raise ValueError("Distance matrix is too small to calculate a phylogenetic tree.")

    # Repeat until matrix dimension is 1x1
    while len(distance_matrix) > 1:
        dist, (row, col) = find_nearest_neighbors(distance_matrix)        # Find nearest neighbors
        clust_name = f"({cluster_names[row]},{cluster_names[col]})"       # Create the cluster's name
        length_dict[clust_name] = dist/2                                  # Assigns the branch length (distance/2) to the cluster's name in the length's dictionary

        # Checks if cluster consists of 2 clusters, example cluster (AB, CDE)
        if cluster_names[row] in name_dict and cluster_names[col] in name_dict:
            newick_tree = (f"({name_dict[cluster_names[row]]}:{dist/2 - length_dict[cluster_names[row]]},"
                         f"{name_dict[cluster_names[col]]}:{dist/2 - length_dict[cluster_names[col]]})")
        # Checks if cluster contains a cluster and an OTU, example cluster (AB, C)
        elif cluster_names[row] in name_dict:
            newick_tree = (f"({name_dict[cluster_names[row]]}:{dist/2 - length_dict[cluster_names[row]]},"
                         f"{cluster_names[col]}:{dist/2})")
        # Checks if cluster contains an OTU and a cluster, example cluster (C, DE)
        elif cluster_names[col] in name_dict:
            newick_tree = (f"({cluster_names[row]}:{dist/2},"
                         f"{name_dict[cluster_names[col]]}:{dist/2 - length_dict[cluster_names[col]]})")
        # Else, cluster contains two OTUs, example (A, B)
        else:
          newick_tree = (f"({cluster_names[row]}:{dist/2},"
                         f"{cluster_names[col]}:{dist/2})")

        name_dict[clust_name] = newick_tree         # Maps cluster name to cluster, example (C,(DE)) = '(C,(D,E)): 22.5'
        cluster_names.append(clust_name)            # Add new cluster into the names of cluster, example [C, D, E, (A,B)]

        # Update the cluster names array
        del cluster_names[max(row, col)]  # Remove the larger index first
        del cluster_names[min(row, col)]

        distance_matrix = rebuild_distance_matrix(distance_matrix, row, col)  # Rebuild distance matrix
    return newick_tree + ";"

def draw_phylo_tree(tree_data):
    """
    Visualize the phylogenetic tree.

    Parameters:
    tree_data: A phylogenetic tree in newick format.

    Returns:
    None.
    """
    with open("tree.nwk", "w") as file:
        file.write(tree_data)
    try: 
        tree = Phylo.read("tree.nwk", "newick")
    except ValueError as e:
        print("Error reading tree.nwk.")

    # Flips branches so deeper clades are displayed at the top
    tree.ladderize()

    # Sets the size of the tree to be 15x20 inches
    fig, ax = plt.subplots(figsize=(7.5, 10))
    Phylo.draw(tree, axes=ax) #, label_func=label_branches)

def generate_phylo_tree(df):
    """
    Takes in a dataframe of gene coding sequences and draw a phylogenetic tree.

    Parameters:
    df (Pandas.DataFrame): The dataframe containing gene sequences.

    Returns:
    None.
    """
    organism_names, distance_matrix = calculate_distance_matrix(df)         # Calculate distance matrix
    tree_info = calculate_phylo_tree(organism_names, distance_matrix)       # Use distnace matrix to calculate phylogenetic tree
    draw_phylo_tree(tree_info)                                              # Draw phylogenetic tree
