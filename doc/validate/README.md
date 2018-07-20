# 验证工具
<p> 支持大部分验证需求 </p>
<p><b>一行代码</b> 一行代码即可满足您的常用验证需求，妈妈再也不用担心我的正则表达式了</p>
<p><b>一个步骤</b> 只需引入jar包就可开车，减少代码耦合，让代码更清爽</p>
<p><b>更多拓展</b> 除了工具提供的校验类外，您还可以添加更多的拓展校验类</p>

## step1
引入jar包
<pre>
    &lt;dependency&gt;
        &lt;groupId&gt;io.github.weechang&lt;/groupId&gt;
        &lt;artifactId&gt;jutil-validate-util&lt;/artifactId&gt;
        &lt;version&gt;1.0.0&lt;/version&gt;
    &lt;/dependency&gt;
</pre>

## 开始浪
### 1.使用API验证
<pre>
    public void validateEmail() {
        boolean vaildate = ValidateUtil.validate("zhangwei_sc@foxmail.com", ValidateEnum.EMAIL);
        assertTrue(vaildate);
    }
</pre>

### 2.使用自定义表达式
<pre>
    public void validateEmail() {
        boolean vaildate = ValidateUtil.validate("zhangwei_sc@foxmail.com", ValidateEnum.EMAIL);
        assertTrue(vaildate);
    }
</pre>

<pre>
    public void validateSelf() {
        boolean vaildate = ValidateUtil.validate("zhangwei_sc@foxmail.com", "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");
        assertTrue(vaildate);
    }
</pre>
