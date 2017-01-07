# Analysis of Densification on Implicit Feedback Sparse Dataset

Given the large number of items (web-pages, videos) available on the Web, users benefit from being shown only items of potential interest to them. One of the tools that address this challenge is recommender systems. Recommendation Systems apply Information Retrieval techniques to select the online information relevant to a given user. Collaborative Filtering (CF) is currently most widely used approach to built Recommendation System. CF techniques use the user's behavior in form of user-item ratings as their information source for prediction. There are major challenges like the sparsity of the rating matrix and the growing nature of data which are faced by CF algorithms. These challenges have been well addressed by Matrix Factorization (MF).
	In this work we focus on studying the collaborative filtering algorithm, Matrix Factorization, considered to be a state-of-the-art approach, and summarize our experiences and lessons learned from experiments on the implicit feedback data domain. Specifically, we suggest that not just the characteristics of a domain (e.g., close connections versus loose connections among users) but also the density(e.g., density of the feedback matrix) property of a dataset helps in predicting the performance of Matrix factorization approach used for a particular recommendation problem.  
 

#Result:
We conducted several experiments and evaluated the performance of Matrix Factorization on real-world implicit feedback datasets from DBLP co-author domain. ALS-Matrix-Factoriation in particular is able to achive good results  as compared to the work presented in "http://ieeexplore.ieee.org/document/7207260/". The increasing running time of the algorithm with the densification of dataset can be improved through parallelization and parameter tuning in general.
nbdfmnbdfnbvnmdf

But as we observed with the increasing density which reduces cold start, the accuracy of the recommender model improved.

#Conclusion
In this work, we summarize our experiences and lessons learned from experiment with state-of-the-art collaborative filtering approach, Matrix Factorization, and implicit feedback dataset(DBLP co-author dataset). Overall, our study shows that knowledge about the characteristics of the domain and of specific data (density of dataset) can be used to guide an analyst towards the most appropriate algorithm to use, thus saving valuable time. The analysis of our results shows that for sparse data datasets (which are quite common in large-scale recommender systems), knowledge about the data domain (specifically, knowledge about the density of dataset)can be used to select the more suitable CF approach.
