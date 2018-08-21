# Cybermiles(CMT) 抢币活动： 抢币代码


Cybermiles推出了抢币活动，具体规则是官方创建10个以太坊账户，每个里面分别有1000个cmt代币以及足够转出的gas，在活动的第一天放出10个账户的私钥前半部分，在后续的某一天放出10个私钥的后半部分，并且将两部分私钥打乱顺序，参与者需要将前后两部分私钥自行组合解锁账户，从而拿到cmt奖励

这个demo的目的是通过web3j的方式穷举私钥对，快速解锁账户并进行交易，提高抢到奖励的成功率

# 准备

环境：macOS，当然windows也是可以的
理论上讲，我们可以直接在以太坊的测试链上进行测试，但是为了达到练手的目的，最好在本地自行搭建以太坊私有链，具体的以太坊私有链搭建相关方法，这里不再赘述，网上相关资料很多

# 第一步：导出Cybermiles的ABI，并编译生成java代理类

要查询某个以太坊代币的合约地址，我们可以去https://etherscan.io/ 搜索代币名称即可，具体方法是
1：进入代币合约地址界面
2：点击Code标签
3：找到Contract Source Code，复制并保存到本地，格式为.sol(本例子中保存为 cybermiles.sol)

通过solc编译合约代码，生成对应的bin和abi

```java (type)
solcjs cybermiles.sol  --bin --abi --optimize -o ./cybermiles
```
之后，在生成的cybermiles文件夹中，可以看到很多文件，我们只需要子类生成的bin和abi就好，即：

```
cybermiles_sol_CyberMilesToken.bin
cybermiles_sol_CyberMilesToken.abi
```

然后通过web3j命令行工具，生成对应的java wrapper code：

```
web3j solidity generate --javaTypes cybermiles_sol_CyberMilesToken.bin cybermiles_sol_CyberMilesToken.abi -o Cybermiles_sol_CyberMilesToken.java -p link.dovakin
```

如果一切顺利的话，当前目录中会出现link->dovakin->Cybermiles_sol_CyberMilesToken.java的目录结构，当然 -p 后的包名参数可以自行更改

以上是Cybermiles代币合约java代理类的生成过程，后续会用到，项目中为了方便本地私有链测试，提供了简单的ERC20代币合约，并基于该合约进行编写

# 第二步：基于web3j编写解锁转账代码

详细代码可以参考项目里UnlockAndTransfer.java的实现

# 第三步：本地私有链测试

1：首先编译本项目代码（基于Maven）
2：启动私有链，并创建至少两个账户(一个为合约创建者账户，剩下的为测试账户)
3：部署项目中的合约（MyToken.sol）
4：更改UnlockAndTransfer.java中的相关变量值
5：执行UnlockAndTransfer.java

