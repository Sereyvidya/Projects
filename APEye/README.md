# 🤖 APEye: Malware Family Classification from API Calls

This project explores techniques to classify malware into families based on their API call behaviors. It includes preprocessing, deep learning, and traditional machine learning approaches.

---

## 📁 Dataset Structure

- `data/APICalls.txt`: Contains a list of all unique API calls.
- `data/malwares/`: Contains 7 subfolders, one for each malware family:
  - `adload`, `bancos`, `onlinegames`, `vbinject`, `vundo`, `winwebsec`, `zwangi`
  - Each subfolder contains text files representing individual malware samples.
  - Each file contains a sequence of API calls made by that malware sample.

---

## 🧪 Approaches Explored

### ✅ Preprocessing

- **Integer Encoding**: Each unique API call is mapped to an integer ID.
- **Word2Vec Embeddings**: API calls are treated like words, and embeddings are generated to represent sequences.
- **Count Vectorization**: For better performance, API call frequencies per sample are collected as feature vectors (like a document-term matrix).

### 🤖 Models

- **Deep Learning**:
  - CNN
  - RNN
  - LSTM  
  *(Results: Did not perform well.)*

- **Classical Machine Learning**:
  - Support Vector Machine (SVM)
  - Random Forest
  - Logistic Regression  
  *(Results: Performed significantly better with count-based features.)*

---

## 📌 How to Run

1. Copy the entire `APEye` folder into your Google Drive.
2. Open [`classifiers.ipynb`](./classifiers.ipynb) in Google Colab.
3. **Update the `PATH` variable** to point to your own copy of the project on Google Drive. For example:
   ```python
   # Change this to your own project path on Google Drive
   PATH = "/content/drive/MyDrive/APEye"
   ```
   This `PATH` variable is used throughout the notebook to read from and write to the appropriate directories. For example:
   ```python
   with open(PATH + "/compiled/encoded_dataset.pkl", "wb") as f:
       pickle.dump(encoded_dataset, f)
   ```
4. Follow the notebook step-by-step to run preprocessing, model training, and evaluation.

> ⚠️ **Important**: If you clone or copy this project to your own Drive, make sure to update the `PATH` variable accordingly to avoid file not found errors.

---

## 🧠 Thought Process

Initially, the project assumed that **the sequence of API calls matters** in classifying malware families. To preserve this sequential information:

- API calls were **integer-encoded** and **Word2Vec embeddings** were generated.
- These sequence-preserving encodings were then fed into **deep learning models** like CNN, RNN, and LSTM, which are known for modeling sequential data.

However, these models **did not yield effective results**.

Based on that, the approach shifted:

- The focus moved to **counting the frequency** of each API call in a sample (regardless of order), forming feature vectors (like a document-term matrix).
- These were then used as input to **traditional machine learning models** — SVM, Random Forest, and Logistic Regression — which significantly outperformed the deep learning methods.

> 🔍 Most of these models were covered in class, and I wanted to demonstrate my understanding through this project.

---

## 🙏 Acknowledgements

- The dataset used in this project was generously provided by **Professor Fabio Di Troia**.
- I would like to thank **Professor Di Troia** for his guidance and support throughout the development of this project.