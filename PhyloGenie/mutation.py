import pandas as pd
import re
import textwrap

def mutate(gene, mutation):
    """
    Mutates the given gene according to the mutation string.

    Parameters:
    gene (str): The original gene sequence.
    mutation (str): The mutation instruction, including the position and type of mutation.
                    (e.g., '5>G' for substitution, '6dupA' for duplication, '7del' for deletion).
    Returns:
    str: The mutated gene sequence.
    """
    copy = list(gene)                   # Copies of gene into a list for easier modification
    for i in range(len(mutation)):      # Loop to find where position and mutation type separate
        if mutation[i].isalpha():
            break
    pos = int(mutation[:i])             # Position for where mutation should occur
    mut = mutation[i:]                  # The type of mutation

    if '>' in mut:                      # Substitution
        copy[pos - 1] = mut[-1]
    if 'dup' in mut:                    # Insertion
        copy.insert(pos - 1, mut[-1])
    if 'del' in mut:                    # Deletion
        copy.pop(pos - 1)
    
    mutated_sequence = ''.join(copy)     # Join the list back into a string and return
    return mutated_sequence              


def mutate_genes(gene, list_of_mutations):
    """
    Apply each mutation to the gene individually.

    gene (str): The gene sequence to mutate.
    list_of_mutations (list): List of mutations to apply to gene.

    Returns:
    list: List of mutated genes.
    """
    gene = re.sub(r"\s+", "", gene)                     # Remove whitespaces from gene sequence        
    for i, mut in enumerate(list_of_mutations):         # Loop through list of mutations and apply mutation
        mut = re.sub(r"\s+", "", mut)   
        list_of_mutations[i + 1] = mutate(gene, mut)    # Index starts at 1, because index 0 is gene
    return list_of_mutations


def read_unmutated_genes():
    """
    Get csv files containing unmutated genes.

    Parameters:
    None.

    Returns:
    pd.Dataframe: A dataframe containing mutated genes with their names.
    """
    prompt = textwrap.dedent("""
    Enter the names of files of unmutated sequences.
    - File must be a csv format, therefore file name should end with .csv (eg. genes.csv).
    - File must contain two columns, 'name' and 'sequence'. 
    - The first entry of 'sequence' must be a full coding sequence (eg. ATGCATGGTA).
    - The following entries of 'sequence' must be mutations such as:
      + Substitution: eg. 48A>G means substitute A to G at position 48.
      + Insertion: eg. 109dupA means insert A at position 109.
      + Deletion: eg. 98del means to delete nucleotide at position 98.
    
    Enter 'stop' when done.
    """)
    
    # Create an empty dataframe for results and ask for file names
    result_df = pd.DataFrame(columns=["name", "sequence"])
    print(prompt)

    while True:
        file = input("Entering: ").strip()
        if file.lower() == "stop":
            break

        try:
            curr_df = pd.read_csv(file)                     # Turn csv file to dataframe
            gene = curr_df['sequence'].iloc[0]              # Get wildtype gene
            mutations = curr_df['sequence'][1:]             # Get list of mutations
            list_of_mutated_genes = mutate_genes(gene, mutations)      # Get list of mutated genes
            
            # Replace mutations with mutated genes and concatenate this dataframe with result dataframe
            curr_df.loc[range(1, len(curr_df)), 'sequence'] = list_of_mutated_genes
            result_df = pd.concat([result_df, curr_df], ignore_index=True)
        except FileNotFoundError:
            print(f"Error: The file '{file}' was not found. Please try again.")   
        except KeyError:
            print("Error: The file must contain 'name' and 'sequence' columns.")   
        except Exception as e:
            print(e)

    return result_df


def read_complete_genes():
    """
    Get csv files containing complete gene coding sequences.

    Parameters:
    None.

    Returns:
    pd.Dataframe: A dataframe containing mutated gene coding sequences and their names.
    """

    prompt = textwrap.dedent("""
    Enter the names of files of unmutated sequences.
    - File must be a csv format, therefore file name should end with .csv (eg. genes.csv).
    - File must contain two columns, 'name' and 'sequence'. 
    - The entries of 'sequence' must be a full coding sequence (eg. ATGCATGGTA).
    
    Enter 'stop' when done.
    """)
    
    result_df = pd.DataFrame(columns=["name", "sequence"])
    print(prompt)

    while True: 
        file = input("Entering: ").strip()
        if file.lower() == 'stop':
            break
        
        try:
            curr_df = pd.read_csv(file)
            result_df = pd.concat([result_df, curr_df], ignore_index=True)
        except FileNotFoundError:
            print(f"Error: The file '{file}' was not found. Please try again.")
        except KeyError:
            print("Error The file must contain 'name' and 'sequence' columns.")
        except Exception as e:
            print(e)
            
    return result_df
