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
3：找到Contract ABI
