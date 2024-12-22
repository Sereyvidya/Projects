import Bio
from Bio.Seq import Seq
from Bio.SeqRecord import SeqRecord
from Bio import SeqIO
import pandas as pd
import numpy as np
import textwrap

def df_to_fasta(df):
    """
    Convert a Pandas dataframe into a FASTA file.

    Parameters:
    df (Pandas.dataframe): Pandas dataframe containing two columns, name and seqeunce.

    Returns:
    None.
    """
    # Remove any whitespaces
    df["sequence"] = df["sequence"].replace(r"\s+", "", regex=True)

    # Maps name to sequence making array of SeqRecord objects
    seq_records = []
    for i, row in df.iterrows():
        seq_records.append(SeqRecord(Seq(row["sequence"]), id=row["name"], description=""))
    
    # Write SeqRecord objects into Fasta file
    SeqIO.write(seq_records, "sequences.fasta", "fasta")
    pass

def find_nearest_neighbors(distance_matrix):
    """
    Finds the minimum distance in the distance matrix that is not 0.

    Parameters:
    distance_matrix (NumPy.array): The distance matrix as a 2D numpy array.

    Returns:
    int: The minimum distance.
    tuple: The indices of the minimum distance.
    """
    min_dist = np.inf                           # Initialize min to largest numpy value
    index = (-1, -1)
    # Iterate through just the upper triangle of the matrix (because it's symmetrical)
    for i in range(len(distance_matrix)):
        for j in range(i + 1, len(distance_matrix[i])):
            if (0 < distance_matrix[i, j] < min_dist):
                min_dist = distance_matrix[i, j]         # Update new minimum distance
                index = (i, j)
    return min_dist, index

def get_input():
    """
    Get user input for processing options.

    Parameters:
    None.

    Returns:
    str: User's input.
    """

    prompt = """
    Enter A to input files containing genes requiring manual mutation.
    Enter B to input files containing complete gene coding sequences.
    Enter C to complete entering.
    Enter D to clear previous output files.
    """
    print(prompt)
    while True:
        ans = input("Entering: ").upper().strip()
        if ans in ['A', 'B', 'C', 'D']:
            return ans
        else:
            print("\nInvalid option. Please enter A, B, C, or D.\n")