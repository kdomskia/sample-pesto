if (config.devServer == undefined) {
    config.mode = "production"
    config.devtool = undefined

    const CompressionPlugin = require('compression-webpack-plugin')
    config.plugins.push(
        new CompressionPlugin({
            filename: '[path][base].gz',
            algorithm: 'gzip',
            test: /\.(js|css|html|svg)$/,
            threshold: 10240,
            minRatio: 0.8
        })
    )
} else {
    config.mode = "development"
}