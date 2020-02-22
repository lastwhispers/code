### 动态规划题目特点

##### 1. 计数

- 有多少种方式走到右下角
- 有多少种方法选出k个数使得和是sum

##### 2.求最大最小值

- 从左上角走到右下角路径的最大数字和
- 最长上升子序列长度

##### 3.求存在性

- 取石子游戏，先手是否必胜
- 能不能选出k个数使得和是sum

### 动态规划四个组成部分：

- 确定状态
  - 研究最优策略的最后一步
  - 化为子问题
- 转移方程
  - 根据子问题定义直接得到
- **初始条件和边界情况**
- 计算顺序
  - 利用之前的计算结果

### 最值型动态规划

> Coin Change
>  假设你有三种硬币，分别面值2元，5元和7元，每种硬币都有足够多，买一本书需要27元，如何用最少的硬币组合正好付清，不需要对方找钱。

#### 动态规划组成部分一：确定状态

- 状态是动态规划的核心
- 解动态规划时需要定义一个数组，数组的每一个元素`f[i]`或者`f[j][j]`代表什么
- 确定状态需要两个意识：
  - 最后一步
  - 子问题

> **最后一步**
>  1-不知道最优策略，但最优策略肯定是K枚硬币![a_1,a_2,...,a_k](https://math.jianshu.com/math?formula=a_1%2Ca_2%2C...%2Ca_k)面值加起来是27
>  2-所以肯定有一枚最后的硬币：![a_k](https://math.jianshu.com/math?formula=a_k)
>   **关键点1：**不关心前面`k-1`枚硬币如何拼出![27-a_k](https://math.jianshu.com/math?formula=27-a_k)，甚至不知道![a_k和K](https://math.jianshu.com/math?formula=a_k%E5%92%8CK)，但是确定了前面的硬币拼出了![27-a_k](https://math.jianshu.com/math?formula=27-a_k)
>   **关键点2：**因为是最优策略，所以拼出![27-a_k](https://math.jianshu.com/math?formula=27-a_k)的硬币数一定要最少，否则矛盾。
>  **子问题**
>  1 - 需要求出最少用多少枚硬币可以拼出![27-a_k](https://math.jianshu.com/math?formula=27-a_k)
>  2 - 原问题是最少用多少枚硬币拼出27
>  3 - 原问题转化成了一个规模更小的子问题：![27-a_k](https://math.jianshu.com/math?formula=27-a_k)
>  4 - 设状态![f(X) = 最少用多少枚硬币拼出X](https://math.jianshu.com/math?formula=f(X)%20%3D%20%E6%9C%80%E5%B0%91%E7%94%A8%E5%A4%9A%E5%B0%91%E6%9E%9A%E7%A1%AC%E5%B8%81%E6%8B%BC%E5%87%BAX)
>  最后一枚硬币只可能是2，5或7
>  若![a_k == 2](https://math.jianshu.com/math?formula=a_k%20%3D%3D%202)，![f(27)](https://math.jianshu.com/math?formula=f(27))应该是![f(27 - 2) + 1](https://math.jianshu.com/math?formula=f(27%20-%202)%20%2B%201)(加上最后的一枚硬币2)
>  若![a_k == 5](https://math.jianshu.com/math?formula=a_k%20%3D%3D%205)，![f(27)](https://math.jianshu.com/math?formula=f(27))应该是![f(27 - 5) + 1](https://math.jianshu.com/math?formula=f(27%20-%205)%20%2B%201)(加上最后的一枚硬币5)
>  若![a_k == 7](https://math.jianshu.com/math?formula=a_k%20%3D%3D%207)，![f(27)](https://math.jianshu.com/math?formula=f(27))应该是![f(27 - 7) + 1](https://math.jianshu.com/math?formula=f(27%20-%207)%20%2B%201)(加上最后的一枚硬币7)
>  要求最少的硬币数：
>  ![f(27) = min{f(27 - 2), f(27 - 5), f(27 - 7)} + 1](https://math.jianshu.com/math?formula=f(27)%20%3D%20min%7Bf(27%20-%202)%2C%20f(27%20-%205)%2C%20f(27%20-%207)%7D%20%2B%201)

#### 动态规划组成部分二：转移方程

- 设状态![f(X) =](https://math.jianshu.com/math?formula=f(X)%20%3D) 最少用多少枚硬币拼出![X](https://math.jianshu.com/math?formula=X)
- 对于任意![X](https://math.jianshu.com/math?formula=X)，![f(27) = min{f(27 - 2), f(27 - 5), f(27 - 7)} + 1](https://math.jianshu.com/math?formula=f(27)%20%3D%20min%7Bf(27%20-%202)%2C%20f(27%20-%205)%2C%20f(27%20-%207)%7D%20%2B%201)

#### 动态规划组成部分三：初始条件和边界情况

- ![X < 0](https://math.jianshu.com/math?formula=X%20%3C%200)时，![f(X) = +\infty](https://math.jianshu.com/math?formula=f(X)%20%3D%20%2B%5Cinfty)
- 初始条件：![f(0) = 0](https://math.jianshu.com/math?formula=f(0)%20%3D%200)

#### 动态规划组成部分三：计算顺序

- 初始条件：![f(0) = 0](https://math.jianshu.com/math?formula=f(0)%20%3D%200)
- 然后计算![f(1),f(2),...,f(27)](https://math.jianshu.com/math?formula=f(1)%2Cf(2)%2C...%2Cf(27))
- 大多数情况都是从小到大地算，这样当算![f(x)](https://math.jianshu.com/math?formula=f(x))时，前面的![f(x-2),f(x-5)和f(x-7)](https://math.jianshu.com/math?formula=f(x-2)%2Cf(x-5)%E5%92%8Cf(x-7))都已经算过了。

### 求最值型动态规划小结

- 1.确定状态
  - 最后一步（最优策略中使用的最后一枚硬币![a_k](https://math.jianshu.com/math?formula=a_k)）
  - 化成子问题（最少的硬币拼出更小的面值![27-a_k](https://math.jianshu.com/math?formula=27-a_k)
- 2.转移方程
  - ![f(X) = min{f(X - 2), f(X - 5), f(X - 7)} + 1](https://math.jianshu.com/math?formula=f(X)%20%3D%20min%7Bf(X%20-%202)%2C%20f(X%20-%205)%2C%20f(X%20-%207)%7D%20%2B%201)
- 3.初始条件和边界情况
  - ![f(0) = 0](https://math.jianshu.com/math?formula=f(0)%20%3D%200)，如果不能拼出![X](https://math.jianshu.com/math?formula=X)，![f(X) = +\infty](https://math.jianshu.com/math?formula=f(X)%20%3D%20%2B%5Cinfty)
- 4.计算顺序
  - ![f(0),f(1),...](https://math.jianshu.com/math?formula=f(0)%2Cf(1)%2C...)

### 计数型动态规划

> Unique Paths
>
> 
>
> 给定m行n列的网格，有一个机器人从左上角(0, 0)出发，每一步可以向下或者向右走一步，问有多少种不同的方式走到右下角
>
> ![img](https:////upload-images.jianshu.io/upload_images/17253929-90839d8817c3c9ad.png?imageMogr2/auto-orient/strip|imageView2/2/w/585/format/webp)

#### 动态规划组成部分一：确定状态

- 最后一步：机器人走到右下角之前的最后一步：

  向右或者向下

  ![img](https:////upload-images.jianshu.io/upload_images/17253929-33fb59e5c5f32a8b.png?imageMogr2/auto-orient/strip|imageView2/2/w/578/format/webp)

- 右下角坐标为(m - 1, n - 1)，那么前一步机器人一定是在(m - 2, n - 1)或者(m - 1, n - 2)

- 子问题 ——从左上角走到(m - 2, n - 1)和从左上角走到(m - 1, n - 2)；假设从左上角走到(m - 2, n - 1)有![X](https://math.jianshu.com/math?formula=X)种方式，从左上角走到(m - 1, n - 2)有![Y](https://math.jianshu.com/math?formula=Y)种方式，那么走到(m - 1, n - 1)就有![X+Y](https://math.jianshu.com/math?formula=X%2BY)种方式。
   问题转化为机器人有多少种方式从左上角走到(m - 2, n - 1)和(m - 1, n - 2)

- 状态：设![f[i][j]](https://math.jianshu.com/math?formula=f%5Bi%5D%5Bj%5D)为机器人有多少种方式从左上角走到![(i,j)](https://math.jianshu.com/math?formula=(i%2Cj))

#### 动态规划组成部分二：转移方程

- 对于任意坐标![(i,j)](https://math.jianshu.com/math?formula=(i%2Cj))，![f[i][j] = f[i - 1][j] + f[i][j - 1]](https://math.jianshu.com/math?formula=f%5Bi%5D%5Bj%5D%20%3D%20f%5Bi%20-%201%5D%5Bj%5D%20%2B%20f%5Bi%5D%5Bj%20-%201%5D)

#### 动态规划组成部分三：初始条件和边界情况

- 初始条件：![f[0][0] = 1](https://math.jianshu.com/math?formula=f%5B0%5D%5B0%5D%20%3D%201)，机器人只有一种方式到左上角
- 边界情况：![i = 0或j = 0](https://math.jianshu.com/math?formula=i%20%3D%200%E6%88%96j%20%3D%200)，则前一步只能有一个方向过来

#### 动态规划组成部分四：计算顺序

- ![f[0][0] = 1](https://math.jianshu.com/math?formula=f%5B0%5D%5B0%5D%20%3D%201)
- 计算第0行：![f[0][0],f[0][1],...,f[0][n-1]](https://math.jianshu.com/math?formula=f%5B0%5D%5B0%5D%2Cf%5B0%5D%5B1%5D%2C...%2Cf%5B0%5D%5Bn-1%5D)
- 计算第1行：![f[1][0],f[1][1],...,f[1][n-1]](https://math.jianshu.com/math?formula=f%5B1%5D%5B0%5D%2Cf%5B1%5D%5B1%5D%2C...%2Cf%5B1%5D%5Bn-1%5D)
- ![...](https://math.jianshu.com/math?formula=...)
- 计算第m-1行：![f[m-1][0],f[m-1][1],...,f[m-1][n-1]](https://math.jianshu.com/math?formula=f%5Bm-1%5D%5B0%5D%2Cf%5Bm-1%5D%5B1%5D%2C...%2Cf%5Bm-1%5D%5Bn-1%5D)
- 答案是![f[m-1][n-1]](https://math.jianshu.com/math?formula=f%5Bm-1%5D%5Bn-1%5D)
   时间复杂度和空间复杂度都是![O(mn)](https://math.jianshu.com/math?formula=O(mn))

### 存在性型动态规划

> 有![n](https://math.jianshu.com/math?formula=n)块石头分别在x轴的![0,1,...,n-1](https://math.jianshu.com/math?formula=0%2C1%2C...%2Cn-1)位置，一只青蛙在石头![0](https://math.jianshu.com/math?formula=0)，想跳到石头![n-1](https://math.jianshu.com/math?formula=n-1)，如果青蛙在第![i](https://math.jianshu.com/math?formula=i)块石头上，它最多可以向右跳距离![a_i](https://math.jianshu.com/math?formula=a_i)，问青蛙能否跳到石头![n-1](https://math.jianshu.com/math?formula=n-1)。

#### 动态规划组成部分一：确定状态

- 最后一步：如果青蛙能跳到最后一块石头![n-1](https://math.jianshu.com/math?formula=n-1)，考虑它跳的最后一步是从石头![i](https://math.jianshu.com/math?formula=i)跳过来的，![i < n-1](https://math.jianshu.com/math?formula=i%20%3C%20n-1)
- 需要满足两个条件：1. 青蛙可以跳到石头![i](https://math.jianshu.com/math?formula=i) 2.最后一步不超过跳跃的最大距离：![n-1-i<=a_i](https://math.jianshu.com/math?formula=n-1-i%3C%3Da_i)
- 子问题——青蛙能不能跳到石头![i(i<n-1)](https://math.jianshu.com/math?formula=i(i%3Cn-1))
- 状态：设![f[j]](https://math.jianshu.com/math?formula=f%5Bj%5D)表示青蛙能不能跳到石头![j](https://math.jianshu.com/math?formula=j)

#### 动态规划组成部分二：转移方程

- 设![f[j]](https://math.jianshu.com/math?formula=f%5Bj%5D)表示青蛙能不能跳到石头![j](https://math.jianshu.com/math?formula=j)，![f[j] = OR_{0<=i<j}(f[i] \&\& (i + a[i] >= j))](https://math.jianshu.com/math?formula=f%5Bj%5D%20%3D%20OR_%7B0%3C%3Di%3Cj%7D(f%5Bi%5D%20%5C%26%5C%26%20(i%20%2B%20a%5Bi%5D%20%3E%3D%20j)))

#### 动态规划组成部分三：初始条件和边界情况

- 设![f[j]](https://math.jianshu.com/math?formula=f%5Bj%5D)表示青蛙能不能跳到石头![j](https://math.jianshu.com/math?formula=j)
- 初始条件：![f[0] = True](https://math.jianshu.com/math?formula=f%5B0%5D%20%3D%20True)，青蛙一开始就在石头![0](https://math.jianshu.com/math?formula=0)

#### 动态规划组成部分四：计算顺序

- 初始化![f[0] = True](https://math.jianshu.com/math?formula=f%5B0%5D%20%3D%20True)
- 计算![f[1],f[2],...,f[n-1]](https://math.jianshu.com/math?formula=f%5B1%5D%2Cf%5B2%5D%2C...%2Cf%5Bn-1%5D)
- 答案是![f[n-1]](https://math.jianshu.com/math?formula=f%5Bn-1%5D)
   时间复杂度：![O(n^2)](https://math.jianshu.com/math?formula=O(n%5E2))，空间复杂度：![O(n)](https://math.jianshu.com/math?formula=O(n))