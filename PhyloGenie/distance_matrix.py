from utils import df_to_fasta
import subprocess
from Bio import AlignIO
from Bio.Phylo.TreeConstruction import DistanceCalculator
import numpy as np

def multiple_sequence_alignment(df):
    """
    Takes a dataframe of genes and do multiple sequence alignment.

    Parameters:
    df: The dataframe of genes. Dataframe should have 'name' (sample names)
    and 'sequence' (gene sequences) columns.

    Returns:
    None.
    """
    # Write to Pandas dataframe into FASTA file called 'sequence.fasta'
    df_to_fasta(df)

    # Run Clustal Omega
    try: 
        subprocess.run([
            'clustalo',
            '-i', 'sequences.fasta',
            '-o', 'aligned_sequences.fasta',
            '--force'  
        ], check=True)
    except subprocess.CalledProcessError as e:
        print(f"Error running Clustal Omega: {e}")

def calculate_distance_matrix(df):
    """
    Takes a dataframe of genes and calculate the distance matrix.

    Parameters:
    df: The dataframe of genes. Dataframe should have 'name' (sample names)
    and 'sequence' (gene sequences) columns.

    Returns:
    list: List of sample names corresponding to the distance matrix rows and columns.
    Np.Array: A 2D NumPy array representing the distance matrix.
    """
    # Do multiple sequence alignment on the sequences
    multiple_sequence_alignment(df)

    # Load the aligned sequences
    alignment = AlignIO.read('aligned_sequences.fasta', "fasta")

    # Calculate the distance matrix using the 'identity' distance measure
    calculator = DistanceCalculator('identity')
    distance_matrix = calculator.get_distance(alignment)

    names = [record.id for record in alignment]     # List of organism names
    distance_matrix = np.array(distance_matrix)     # Convert distance_matrix into 2D NumPy array
    return names, distance_matrix

def rebuild_distance_matrix(distance_matrix, row, col):
    """
    Rebuild distance matrix after clustering.

    Parameters:
    matrix (NumPy.array): The distance matrix as a 2D numpy array.
    row (int): The row index to delete.
    col (int): The column index to delete.

    Returns:
    None.
    """
    # Calculate the new row with stances from this cluster and all other clusters
    new_row = []
    for i in range(len(distance_matrix)):
        if i != row and i != col:
            new_row.append((distance_matrix[i, row] + distance_matrix[i, col]) / 2)   # d(X, AB) = (d(X, A) + d(X, B)) / 2
    new_row = np.array(new_row)     # Convert to numpy array to add to distance matrix later

    distance_matrix = np.delete(distance_matrix, [row, col], axis=0)    # Delete the old clusters' rows from the distance matrix
    distance_matrix = np.delete(distance_matrix, [row, col], axis=1)    # Delete the old clusters' columns from the distance matrix

    # Add the new row to the distance matrix
    distance_matrix = np.vstack([distance_matrix, new_row])
    distance_matrix = np.column_stack([distance_matrix, np.append(new_row, 0)])
    return distance_matrix